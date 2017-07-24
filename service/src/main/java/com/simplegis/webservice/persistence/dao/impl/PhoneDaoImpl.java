package com.simplegis.webservice.persistence.dao.impl;

import com.simplegis.webservice.persistence.dao.PhoneDao;
import com.simplegis.webservice.persistence.entity.City;
import com.simplegis.webservice.persistence.entity.Phone;
import com.simplegis.webservice.persistence.util.BatchUpdateWithGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Phone data access object.
 */
public class PhoneDaoImpl extends JdbcDaoSupport implements PhoneDao {

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getAll() {
        String sql = "SELECT * FROM simplegisdb.phone p";

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Phone>());
    }

    @Override
    @Transactional(readOnly = true)
    public Phone getById(BigInteger id) {
        String sql = "SELECT * FROM simplegisdb.phone p WHERE p.id = ?";
        Object[] args = {id};

        return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Phone>());
    }

    @Override
    @Transactional
    public Integer update(Phone phone) {
        String sql = "UPDATE simplegisdb.phone p SET p.number = ?, p.organization_id = ?, p.version = ?" +
                " WHERE p.id = ? AND p.version = ?";

        return getJdbcTemplate().update(sql,
                phone.getNumber(), phone.getOrganizationId(), phone.getVersion() + 1, phone.getId(), phone.getVersion());
    }

    @Override
    @Transactional
    public Phone insert(Phone phone) {
        String sql = "INSERT INTO simplegisdb.phone (number, organization_id, version)" +
                " VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, phone.getNumber());
            preparedStatement.setLong(++i, phone.getOrganizationId().longValue());
            preparedStatement.setLong(++i, 0);


            return preparedStatement;

        }, keyHolder);

        phone.setId(BigInteger.valueOf(keyHolder.getKey().longValue()));
        return phone;
    }

    @Override
    @Transactional
    public List<Phone> batchInsert(List<Phone> phones) {
        String sql = "INSERT INTO simplegisdb.phone (number, organization_id, version)" +
                " VALUES (?, ?, ?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Phone phone = phones.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, phone.getNumber());
                        preparedStatement.setLong(++index, phone.getOrganizationId().longValue());
                        preparedStatement.setLong(++index, 0);

                    }

                    @Override
                    public int getBatchSize() {
                        return phones.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < phones.size(); i++) {
            phones.get(i).setId(BigInteger.valueOf((long) generatedKeys.get(i).get("id")));
        }

        return phones;
    }

    @Override
    @Transactional
    public Integer delete(Phone phone) {
        //ToDo: implement;
        return null;
    }
}
