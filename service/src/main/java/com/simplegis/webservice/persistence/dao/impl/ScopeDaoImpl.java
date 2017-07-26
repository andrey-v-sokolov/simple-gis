package com.simplegis.webservice.persistence.dao.impl;


import com.simplegis.webservice.persistence.dao.ScopeDao;
import com.simplegis.webservice.persistence.entity.Scope;
import com.simplegis.webservice.persistence.util.BatchUpdateWithGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Scope data access object.
 */
@Repository
public class ScopeDaoImpl implements ScopeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Scope> getAll() {
        String sql = "SELECT * FROM simplegisdb.scope s";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Scope.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Scope getById(Long id) {
        String sql = "SELECT * FROM simplegisdb.scope s WHERE s.id = ?";
        Object[] args = {id};

        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(Scope.class));
    }

    @Override
    @Transactional
    public Integer update(Scope scope) {
        String sql = "UPDATE simplegisdb.scope SET name = ?, version = ?"
                + " WHERE id = ? AND version = ?";

        return jdbcTemplate.update(sql,
                scope.getName(), scope.getVersion() + 1, scope.getId(), scope.getVersion());
    }

    @Override
    @Transactional
    public Scope insert(Scope scope) {
        String sql = "INSERT INTO simplegisdb.scope (name, version, key_words)"
                + " VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, scope.getName());
            preparedStatement.setLong(++i, 0);
            preparedStatement.setString(++i, scope.getKeyWords());

            return preparedStatement;

        }, keyHolder);

        scope.setId(keyHolder.getKey().intValue());
        scope.setVersion(0);
        return scope;
    }

    @Override
    @Transactional
    public List<Scope> batchInsert(List<Scope> scopes) {
        String sql = "INSERT INTO simplegisdb.scope (name, version)"
                + " VALUES (?, ?, ?)";

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
                        preparedStatement.setString(++index, scope.getKeyWords());

                    }

                    @Override
                    public int getBatchSize() {
                        return scopes.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < scopes.size(); i++) {
            scopes.get(i).setId((int) generatedKeys.get(i).get("id"));
            scopes.get(i).setVersion(0);
        }

        return scopes;
    }
}
