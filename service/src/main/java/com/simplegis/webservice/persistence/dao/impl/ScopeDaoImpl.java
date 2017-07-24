package com.simplegis.webservice.persistence.dao.impl;


import com.simplegis.webservice.persistence.dao.ScopeDao;
import com.simplegis.webservice.persistence.entity.Scope;
import com.simplegis.webservice.persistence.util.BatchUpdateWithGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Scope data access object.
 */
@Repository
public class ScopeDaoImpl extends JdbcDaoSupport implements ScopeDao {

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Scope> getAll() {
        String sql = "SELECT * FROM simplegisdb.scope s";

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Scope>());
    }

    @Override
    @Transactional(readOnly = true)
    public Scope getById(BigInteger id) {
        String sql = "SELECT * FROM simplegisdb.scope s WHERE s.id = ?";
        Object[] args = {id};

        return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Scope>());
    }

    @Override
    @Transactional
    public Integer update(Scope scope) {
        String sql = "UPDATE simplegisdb.scope s SET s.name = ?, s.version = ?"
                + " WHERE s.id = ? AND s.version = ?";

        return getJdbcTemplate().update(sql,
                scope.getName(), scope.getVersion() + 1, scope.getId(), scope.getVersion());
    }

    @Override
    @Transactional
    public Scope insert(Scope scope) {
        String sql = "INSERT INTO simplegisdb.scope (name, version)"
                + " VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, scope.getName());
            preparedStatement.setLong(++i, 0);


            return preparedStatement;

        }, keyHolder);

        scope.setId(keyHolder.getKey().intValue());
        return scope;
    }

    @Override
    @Transactional
    public List<Scope> batchInsert(List<Scope> scopes) {
        String sql = "INSERT INTO simplegisdb.scope (name, version)"
                + " VALUES (?, ?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Scope scope = scopes.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, scope.getName());
                        preparedStatement.setLong(++index, 0);

                    }

                    @Override
                    public int getBatchSize() {
                        return scopes.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < scopes.size(); i++) {
            scopes.get(i).setId((int) generatedKeys.get(i).get("id"));
        }

        return scopes;
    }
}
