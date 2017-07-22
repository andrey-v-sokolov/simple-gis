package com.simplegis.webservice.persistence.dao;

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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Cities dao.
 */
@Repository
public class CityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchUpdateWithGeneratedKeys batchUpdateWithGeneratedKeys;

    /**
     * Get all cities.
     *
     * @return list of cities.
     */
    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return jdbcTemplate.query("SELECT * FROM simplegisdb.city", new BeanPropertyRowMapper<>(City.class));
    }

    /**
     * Get city by id.
     *
     * @param id of a city
     * @return city or null
     */
    @Transactional(readOnly = true)
    public City getCityById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM simplegisdb.city WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(City.class));
    }

    /**
     * Update city by id.
     *
     * @param city with new data
     * @return 1 if updated 0 if not
     */
    @Transactional
    public Integer updateCityById(City city) {
        return jdbcTemplate.update("UPDATE simplegisdb.city c SET c.name = ?, c.area = ?, c.population = ? WHERE c.id = ?",
                city.getName(), city.getArea(), city.getPopulation(), city.getId());
    }

    /**
     * Add city.
     *
     * @param city to insert into db
     * @return inserted city
     */
    public City insertCity(City city) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO simplegisdb.city (name, area, population)"
                                    + " VALUES (?, ?, ?)", new String[]{"id"});
            int i = 0;
            preparedStatement.setString(++i, city.getName());
            preparedStatement.setBigDecimal(++i, city.getArea());
            preparedStatement.setInt(++i, city.getPopulation());
            return preparedStatement;
        }, keyHolder);
        city.setId(keyHolder.getKey().longValue());

        return city;
    }

    /**
     * Batch cities add.
     *
     * @param cities list fo cities to insert into db
     * @return list of inserted cities
     */
    public List<City> batchInsertCities(List<City> cities) {

        List<Map<String, Object>> generatedKeys;
        generatedKeys = batchUpdateWithGeneratedKeys.batchUpdate(
                "INSERT INTO simplegisdb.city (name, area, population) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        City city = cities.get(i);

                        int index = 0;
                        preparedStatement.setString(++index, city.getName());
                        preparedStatement.setBigDecimal(++index, city.getArea());
                        preparedStatement.setInt(++index, city.getPopulation());
                    }

                    @Override
                    public int getBatchSize() {
                        return cities.size();
                    }
                },
                new GeneratedKeyHolder());

        for (int i = 0; i < cities.size(); i++) {
            cities.get(i).setId((Long) generatedKeys.get(i).get("id"));
        }

        return cities;
    }
}
