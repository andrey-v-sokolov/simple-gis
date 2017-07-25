package com.simplegis.webservice.service;

import com.simplegis.webservice.persistence.dao.CityDao;
import com.simplegis.webservice.persistence.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * City service.
 */
@Service
public class CityService {
    private static final Logger LOG = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityDao cityDao;

    /**
     * Service method for getting all cities.
     *
     * @return city List
     */
    public List<City> getAll() {
        return cityDao.getAll();
    }

    /**
     * Service method for getting specified city.
     *
     * @param id of a specified city
     * @return specified city or null if it does not exist
     */
    public City getById(Long id) {
        return cityDao.getById(id);
    }

    /**
     * Service method for update city.
     *
     * @param city to update
     * @return 1 if city was updated 0 if not
     */
    public Integer update(City city) {
        return cityDao.update(city);
    }

    /**
     * Service method for insert a city.
     *
     * @param city to insert
     * @return inserted city with generated id
     */
    public City insert(City city) {
        return cityDao.insert(city);
    }

    /**
     * Service method for batch cities insert.
     *
     * @param cities list to insert
     * @return list of inserted cities
     */
    public List<City> batchInsert(List<City> cities) {
        return cityDao.batchInsert(cities);
    }

    /**
     * Service method for search cities by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found cities
     */
    public List<City> getByName(String name) {
        return cityDao.getByName(name);
    }

    /**
     * Service method for search cities by its area.
     *
     * @param minimalArea of city
     * @param maximumArea of city
     * @return list of found cities
     */
    public List<City> getByArea(BigDecimal minimalArea, BigDecimal maximumArea) {
        return cityDao.getByArea(minimalArea, maximumArea);
    }

    /**
     * Service method for search cities by its population.
     *
     * @param minimalPopulation of city
     * @param maximumPopulation of city
     * @return list of found cities
     */
    public List<City> getByPopulation(Integer minimalPopulation, Integer maximumPopulation) {
        return cityDao.getByPopulation(minimalPopulation, maximumPopulation);
    }
}
