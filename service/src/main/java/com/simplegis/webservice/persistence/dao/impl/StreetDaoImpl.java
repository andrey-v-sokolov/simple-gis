package com.simplegis.webservice.persistence.dao.impl;


import com.simplegis.webservice.persistence.dao.StreetDao;
import com.simplegis.webservice.persistence.entity.Street;
import com.simplegis.webservice.persistence.util.BatchUpdateWithGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Street data access object.
 */
@Repository
public class StreetDaoImpl extends JdbcDaoSupport implements StreetDao {

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Street> getAll() {
        String sql = "SELECT * FROM simplegisdb.street st";

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Street>());
    }

    @Override
    @Transactional(readOnly = true)
    public Street getById(BigInteger id) {
        String sql = "SELECT * FROM simplegisdb.street st WHERE st.id = ?";
        Object[] args = {id};

        return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Street>());
    }

    @Override
    @Transactional
    public Integer update(Street street) {
        String sql = "UPDATE simplegisdb.street st SET st.name = ?, st.city_id = ?, st.length = ?,  st.version = ?"
                + " WHERE st.id = ? AND st.version = ?";

        return getJdbcTemplate().update(sql,
                street.getName(), street.getCityId(), street.getLength(), street.getVersion() + 1, street.getId(), street.getVersion());
    }

    @Override
    @Transactional
    public Street insert(Street street) {
        String sql = "INSERT INTO simplegisdb.street (name, length, city_id, version)"
                + " VALUES (?, ?, ? ,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, street.getName());
            preparedStatement.setBigDecimal(++i, street.getLength());
            preparedStatement.setLong(++i, street.getCityId().longValue());
            preparedStatement.setLong(++i, 0);


            return preparedStatement;

        }, keyHolder);

        street.setId(BigInteger.valueOf(keyHolder.getKey().longValue()));
        return street;
    }

    @Override
    @Transactional
    public List<Street> batchInsert(List<Street> streets) {
        String sql = "INSERT INTO simplegisdb.street (name, length, city_id, version)"
                + " VALUES (?, ?, ? ,?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Street street = streets.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, street.getName());
                        preparedStatement.setBigDecimal(++index, street.getLength());
                        preparedStatement.setLong(++index, street.getCityId().longValue());
                        preparedStatement.setLong(++index, 0);

                    }

                    @Override
                    public int getBatchSize() {
                        return streets.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < streets.size(); i++) {
            streets.get(i).setId(BigInteger.valueOf((long) generatedKeys.get(i).get("id")));
        }

        return streets;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Street> getByName(String name) {
        String sql = "SELECT * FROM simplegisdb.street st WHERE st.name LIKE ?";

        String nToken = "%" + name + "%";
        Object[] args = {nToken};

        return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<Street>());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Street> getByLength(BigDecimal minimalLength, BigDecimal maximumLength) {
        String sql = "SELECT * FROM simplegisdb.street st WHERE st.length >= ? AND st.length <= ?";

        List<Object> args = new LinkedList<>();

        if (maximumLength == null || BigDecimal.ZERO.equals(maximumLength)) {
            sql = "SELECT * FROM simplegisdb.street st WHERE st.length >= ?";
        } else {
            args.add(maximumLength);
        }
        if (minimalLength == null || BigDecimal.ZERO.equals(minimalLength)) {
            sql = "SELECT * FROM simplegisdb.street st WHERE st.length <= ?";
        } else {
            args.add(minimalLength);
        }

        if (args.isEmpty()) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, args.toArray(), new BeanPropertyRowMapper<Street>());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Street> getByCityIdAndName(BigInteger cityId, String name) {
        String sql = "SELECT * FROM simplegisdb.street st WHERE st.city_id = ? AND st.name LIKE ?";

        String nToken = "%" + name + "%";
        Object[] args = {cityId, nToken};

        return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<Street>());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Street> getByCityIdAndLength(BigInteger cityId, BigDecimal minimalLength, BigDecimal maximumLength) {
        String sql = "SELECT * FROM simplegisdb.street st WHERE st.city_id = ? AND st.length >= ? AND st.length <= ?";

        List<Object> args = new LinkedList<>();
        args.add(cityId);

        if (maximumLength == null || BigDecimal.ZERO.equals(maximumLength)) {
            sql = "SELECT * FROM simplegisdb.street st WHERE st.city_id = ? AND st.length >= ?";
        } else {
            args.add(maximumLength);
        }
        if (minimalLength == null || BigDecimal.ZERO.equals(minimalLength)) {
            sql = "SELECT * FROM simplegisdb.street st WHERE st.city_id = ? AND st.length <= ?";
        } else {
            args.add(minimalLength);
        }

        if (args.size() == 1) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, args.toArray(), new BeanPropertyRowMapper<Street>());
        }
    }
}
