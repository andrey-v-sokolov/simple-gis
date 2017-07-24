package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.City;

import java.math.BigDecimal;
import java.util.List;

/**
 * City specific data access object interface extension.
 */
public interface CityDao extends GenericDao<City> {

    /**
     *
     * @param name
     * @return
     */
    List<City> getByName(String name);

    /**
     *
     * @param minimalArea
     * @param maximumArea
     * @return
     */
    List<City> getByArea(BigDecimal minimalArea, BigDecimal maximumArea);

    /**
     *
     * @param minimalPopulation
     * @param maximumPopulation
     * @return
     */
    List<City> getByPopulation(Integer minimalPopulation, Integer maximumPopulation);
}
