package com.simplegis.webservice.persistence.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of a batch insert from https://jira.spring.io/browse/SPR-1836 (slightly modified).
 */
@Component
public class BatchUpdateWithGeneratedKeys {
    private static final Logger LOG = LoggerFactory.getLogger(BatchUpdateWithGeneratedKeys.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * butchUpdate execution method with ability of return generated keys.
     *
     * @param sql                to execute in batch mode
     * @param pss                batch prepared statement setter
     * @param generatedKeyHolder key holder
     * @return generated keys list
     * @throws DataAccessException in case of sql errors.
     */
    public List<Map<String, Object>> batchUpdate(final String sql,
                                                 final BatchPreparedStatementSetter pss, final KeyHolder generatedKeyHolder)
            throws DataAccessException {

        return (List<Map<String, Object>>) jdbcTemplate.execute(
                conn -> conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS),
                (PreparedStatementCallback) ps -> {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Executing batch SQL update and returning "
                                + "generated keys [" + sql + "]");
                    }

                    try {
                        int batchSize = pss.getBatchSize();
                        int totalRowsAffected = 0;
                        int[] rowsAffected = new int[batchSize];
                        List<Map<String, Object>> generatedKeys = generatedKeyHolder.getKeyList();
                        generatedKeys.clear();
                        ResultSet keys = null;
                        for (int i = 0; i < batchSize; i++) {
                            pss.setValues(ps, i);
                            rowsAffected[i] = ps.executeUpdate();
                            totalRowsAffected += rowsAffected[i];
                            try {
                                keys = ps.getGeneratedKeys();
                                if (keys != null) {
                                    RowMapper<Map<String, Object>> rowMapper = new ColumnMapRowMapper();
                                    RowMapperResultSetExtractor<Map<String, Object>> rse =
                                            new RowMapperResultSetExtractor<>(rowMapper, 1);
                                    generatedKeys.addAll(rse.extractData(keys));
                                }
                            } finally {
                                JdbcUtils.closeResultSet(keys);
                            }
                        }

                        if (LOG.isDebugEnabled()) {
                            LOG.debug("SQL batch update affected "
                                    + totalRowsAffected + " rows and returned "
                                    + generatedKeys.size() + " keys");
                        }

                        return generatedKeys;
                    } finally {
                        if (pss instanceof ParameterDisposer) {
                            ((ParameterDisposer) pss).cleanupParameters();
                        }
                    }
                });
    }
}
