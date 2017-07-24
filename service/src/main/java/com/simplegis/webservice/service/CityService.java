package com.simplegis.webservice.service;

import com.simplegis.webservice.persistence.dao.CityDao;
import com.simplegis.webservice.persistence.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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
     *
     * @return
     */
    public List<City> getAll() {
        return cityDao.getAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public City getById(BigInteger id) {
        return cityDao.getById(id);
    }

    /**
     *
     * @param city
     * @return
     */
    public Integer update(City city) {
        return cityDao.update(city);
    }

    /**
     *
     * @param city
     * @return
     */
    public City insert(City city) {
        return cityDao.insert(city);
    }

    /**
     *
     * @param cities
     * @return
     */
    public List<City> batchInsert(List<City> cities) {
        return cityDao.batchInsert(cities);
    }

    /**
     *
     * @param name
     * @return
     */
    public List<City> getByName(String name) {
        return cityDao.getByName(name);
    }

    /**
     *
     * @param minimalArea
     * @param maximumArea
     * @return
     */
    public List<City> getByArea(BigDecimal minimalArea, BigDecimal maximumArea) {
        return cityDao.getByArea(minimalArea, maximumArea);
    }

    /**
     *
     * @param minimalPopulation
     * @param maximumPopulation
     * @return
     */
    public List<City> getByPopulation(Integer minimalPopulation, Integer maximumPopulation) {
        return cityDao.getByPopulation(minimalPopulation, maximumPopulation);
    }
}
