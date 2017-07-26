package com.simplegis.webservice.persistence.dao.impl;


import com.simplegis.webservice.persistence.dao.OrganizationDao;
import com.simplegis.webservice.persistence.entity.Organization;
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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Organization data access object.
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getAll() {
        String sql = "SELECT * FROM simplegisdb.organization o";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getById(Long id) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.id = ?";
        Object[] args = {id};

        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional
    public Integer update(Organization organization) {
        //Using modified field as version for optimistic locking.
        String sql = "UPDATE simplegisdb.organization"
                + " SET name = ?, street = ?, scope = ?, building = ?, city =?, www = ?, modified = ?"
                + " WHERE id = ? AND modified = ?";

        return jdbcTemplate.update(sql,
                organization.getName(), organization.getStreet(), organization.getScope(), organization.getBuilding(),
                organization.getCity(), organization.getWww(), new Timestamp(Calendar.getInstance().getTime().getTime()),
                organization.getId(), organization.getModified());
    }

    @Override
    @Transactional
    public Organization insert(Organization organization) {
        String sql = "INSERT INTO simplegisdb.organization (name, city, street, building, scope, modified, www)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, organization.getName());
            preparedStatement.setLong(++i, organization.getCity());
            preparedStatement.setLong(++i, organization.getStreet());
            preparedStatement.setInt(++i, organization.getBuilding());
            preparedStatement.setInt(++i, organization.getScope());
            preparedStatement.setTimestamp(++i, new Timestamp(Calendar.getInstance().getTime().getTime()));
            preparedStatement.setString(++i, organization.getWww());

            return preparedStatement;

        }, keyHolder);

        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }

    @Override
    @Transactional
    public List<Organization> batchInsert(List<Organization> organizations) {
        String sql = "INSERT INTO simplegisdb.organization (name, city, street, building, scope, modified, www)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Organization organization = organizations.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, organization.getName());
                        preparedStatement.setLong(++index, organization.getCity());
                        preparedStatement.setLong(++index, organization.getStreet());
                        preparedStatement.setInt(++index, organization.getBuilding());
                        preparedStatement.setInt(++index, organization.getScope());
                        preparedStatement.setTimestamp(++index, new Timestamp(Calendar.getInstance().getTime().getTime()));
                        preparedStatement.setString(++index, organization.getWww());
                    }

                    @Override
                    public int getBatchSize() {
                        return organizations.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < organizations.size(); i++) {
            organizations.get(i).setId((long) generatedKeys.get(i).get("id"));
        }

        return organizations;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getByName(String name) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.name LIKE ?";

        String nToken = "%" + name + "%";
        Object[] args = {nToken};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getByCityId(Long cityId) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.city = ?";
        Object[] args = {cityId};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getByCityIdAndStreetId(Long cityId, Long streetId) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.city = ? AND o.street = ?";
        Object[] args = {cityId, streetId};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getByCityIdAndStreetIdAndBuilding(Long cityId, Long streetId, Integer building) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.city = ? AND o.street = ? AND o.building = ?";
        Object[] args = {cityId, streetId, building};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getByScopeNameOrOrganizationNameAndGeoToken(String organizationToken, String geoToken) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE "
                + "(o.scope IN (SELECT s.id FROM simplegisdb.scope s WHERE s.name LIKE ? OR  s.key_words LIKE ?)"
                + " OR o.name LIKE ?) "
                + "AND (o.city IN (SELECT c.id FROM simplegisdb.city c WHERE c.name LIKE ?) "
                + "OR o.street IN (SELECT st.id FROM simplegisdb.street st WHERE st.name LIKE ?))";

        String oToken = "%" + organizationToken.toLowerCase() + "%";
        String gToken = "%" + geoToken.toLowerCase() + "%";
        Object[] args = {oToken, oToken, oToken, gToken, gToken};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }

    @Override
    public List<Organization> getOrganizationAddedOrModifiedSince(Timestamp timestamp) {
        String sql = "SELECT * FROM simplegisdb.organization o WHERE o.modified >= ?";
        Object[] args = {timestamp};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(Organization.class));
    }
}
