package com.simplegis.webservice.persistence.dao.impl;

import com.simplegis.webservice.persistence.dao.CityDao;
import com.simplegis.webservice.persistence.entity.City;
import com.simplegis.webservice.persistence.util.BatchUpdateWithGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Cities data access object.
 */
@Repository
public class CityDaoImpl implements CityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    @Override
    @Transactional(readOnly = true)
    public List<City> getAll() {
        String sql = "SELECT * FROM simplegisdb.city c";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(City.class));
    }

    @Override
    @Transactional(readOnly = true)
    public City getById(Long id) {
        String sql = "SELECT * FROM simplegisdb.city c WHERE c.id = ?";
        Object[] args = {id};

        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(City.class));
    }

    @Override
    @Transactional
    public Integer update(City city) {
        String sql = "UPDATE simplegisdb.city SET name = ?, area = ?, population = ?, version = ?"
                + " WHERE id = ? AND version = ?";

        return jdbcTemplate.update(sql,
                city.getName(), city.getArea(), city.getPopulation(), city.getVersion() + 1, city.getId(), city.getVersion());
    }

    @Override
    @Transactional
    public City insert(City city) {
        String sql = "INSERT INTO simplegisdb.city (name, area, population, version) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String[] columnNames = {"id"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);

            int i = 0;
            preparedStatement.setString(++i, city.getName());
            preparedStatement.setBigDecimal(++i, city.getArea());
            preparedStatement.setInt(++i, city.getPopulation());
            preparedStatement.setInt(++i, 0);

            return preparedStatement;

        }, keyHolder);

        city.setId(keyHolder.getKey().longValue());
        return city;
    }

    @Override
    @Transactional
    public List<City> batchInsert(List<City> cities) {
        String sql = "INSERT INTO simplegisdb.city (name, area, population, version) VALUES (?, ?, ?, ?)";

        List<Map<String, Object>> generatedKeys;

        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        City city = cities.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, city.getName());
                        preparedStatement.setBigDecimal(++index, city.getArea());
                        preparedStatement.setInt(++index, city.getPopulation());
                        preparedStatement.setInt(++index, 0);

                    }

                    @Override
                    public int getBatchSize() {
                        return cities.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < cities.size(); i++) {
            cities.get(i).setId((long) generatedKeys.get(i).get("id"));
        }

        return cities;
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getByName(String name) {
        String sql = "SELECT * FROM simplegisdb.city c WHERE c.name LIKE ?";
        String nToken = "%" + name + "%";
        Object[] args = {nToken};

        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(City.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getByArea(BigDecimal minimalArea, BigDecimal maximumArea) {
        String sql = "SELECT * FROM simplegisdb.city c WHERE c.area >= ? AND c.area <= ?";

        List<Object> args = new LinkedList<>();

        if (minimalArea == null || BigDecimal.ZERO.equals(minimalArea)) {
            sql = "SELECT * FROM simplegisdb.city c WHERE c.area <= ?";
        } else {
            args.add(minimalArea);
        }
        if (maximumArea == null || BigDecimal.ZERO.equals(maximumArea)) {
            sql = "SELECT * FROM simplegisdb.city c WHERE c.area >= ?";
        } else {
            args.add(maximumArea);
        }

        if (args.isEmpty()) {
            return null;
        } else {
            return jdbcTemplate.query(sql, args.toArray(), new BeanPropertyRowMapper<>(City.class));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getByPopulation(Integer minimalPopulation, Integer maximumPopulation) {
        String sql = "SELECT * FROM simplegisdb.city c WHERE c.population >= ? AND c.population <= ?";

        List<Object> args = new LinkedList<>();

        if (minimalPopulation == null || minimalPopulation == 0) {
            sql = "SELECT * FROM simplegisdb.city c WHERE c.population <= ?";
        } else {
            args.add(minimalPopulation);
        }
        if (maximumPopulation == null || maximumPopulation == 0) {
            sql = "SELECT * FROM simplegisdb.city c WHERE c.population >= ?";
        } else {
            args.add(maximumPopulation);
        }

        if (args.isEmpty()) {
            return null;
        } else {
            return jdbcTemplate.query(sql, args.toArray(), new BeanPropertyRowMapper<>(City.class));
        }
    }
}
