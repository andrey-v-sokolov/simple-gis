package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Cities dao.
 */
@Repository
public class CityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get all cities.
     *
     * @return list of cities.
     */
    public List<City> getAllCities() {
        return jdbcTemplate.query("SELECT * FROM simplegisdb.city", new BeanPropertyRowMapper<>(City.class));
    }

    /**
     * Get city by id.
     *
     * @param id of a city
     * @return city or null
     */
    public City getCityById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM simplegisdb.city WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(City.class));
    }

    /**
     * Update city by id.
     *
     * @param city with new data
     * @return 1 if updated 0 if not
     */
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

        /*
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO simplegisdb.city (name, area, population) VALUES (?, ?, ?)",
                new Object[] {city.getName(), city.getArea(), city.getPopulation()},
                keyHolder, new String[]{"id"});
        city.setId(keyHolder.getKey().longValue());
        */
        return city;
    }

    /**
     * Batch cities add.
     *
     * @param cities list fo cities to insert into db
     * @return list of inserted cities
     */
    public List<City> batchInsertCities(List<City> cities) {
        return null;
    }
}
