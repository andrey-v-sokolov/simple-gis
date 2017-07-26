package com.simplegis.webservice.persistence.dao.impl;

import com.simplegis.webservice.persistence.dao.PhoneDao;
import com.simplegis.webservice.persistence.entity.Phone;
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
 * Phone data access object.
 */
@Repository
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getAll() {
        String sql = "SELECT * FROM simplegisdb.phone p";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Phone.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Phone getById(Long id) {
        String sql = "SELECT * FROM simplegisdb.phone p WHERE p.id = ?";
        Object[] args = {id};

        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(Phone.class));
    }

    @Override
    @Transactional
    public Integer update(Phone phone) {
        String sql = "UPDATE simplegisdb.phone SET number = ?, organization_id = ?, version = ?"
                + " WHERE id = ? AND version = ?";

        return jdbcTemplate.update(sql,
                phone.getNumber(), phone.getOrganizationId(), phone.getVersion() + 1, phone.getId(), phone.getVersion());
    }

    @Override
    @Transactional
    public Phone insert(Phone phone) {
        String sql = "INSERT INTO simplegisdb.phone (number, organization_id, version)"
                + " VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, phone.getNumber());
            preparedStatement.setLong(++i, phone.getOrganizationId());
            preparedStatement.setLong(++i, 0);


            return preparedStatement;

        }, keyHolder);

        phone.setId(keyHolder.getKey().longValue());
        phone.setVersion(0);
        return phone;
    }

    @Override
    @Transactional
    public List<Phone> batchInsert(List<Phone> phones) {
        String sql = "INSERT INTO simplegisdb.phone (number, organization_id, version)"
                + " VALUES (?, ?, ?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Phone phone = phones.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, phone.getNumber());
                        preparedStatement.setLong(++index, phone.getOrganizationId());
                        preparedStatement.setLong(++index, 0);

                    }

                    @Override
                    public int getBatchSize() {
                        return phones.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < phones.size(); i++) {
            phones.get(i).setId((long) (int) generatedKeys.get(i).get("id"));
            phones.get(i).setVersion(0);
        }

        return phones;
    }

    @Override
    @Transactional
    public Integer delete(Phone phone) {
        String sql = "DELETE FROM simplegisdb.phone p WHERE p.id = ?";
        Object[] args = {phone.getId()};

        return jdbcTemplate.update(sql, args);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getByOrganizationId(Long orgId) {
        String sql = "SELECT * FROM simplegisdb.phone p WHERE p.organization_id = ?";
        Object[] args = {orgId};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Phone.class));
    }
}
