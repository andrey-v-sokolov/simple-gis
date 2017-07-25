package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.City;

import java.math.BigDecimal;
import java.util.List;

/**
 * City specific data access object interface extension.
 */
public interface CityDao extends GenericDao<City> {

    /**
     * DAO method for search cities by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found cities
     */
    List<City> getByName(String name);

    /**
     * DAO method for search cities by its area.
     * <p>
     * Min and max parameters are inclusive;
     * If min and max parameters are equal then strict value will be searched;
     * If min parameter is null or equals 0 then search will be performed as less than or equal to max;
     * If max parameter is null or equals 0 then serrch will be performed as greater than or equal to min;
     * </p>
     *
     * @param minimalArea of city
     * @param maximumArea of city
     * @return list of found cities
     */
    List<City> getByArea(BigDecimal minimalArea, BigDecimal maximumArea);

    /**
     * DAO method for search cities by its population.
     * <p>
     * Min and max parameters are inclusive;
     * If min and max parameters are equal then strict value will be searched;
     * If min parameter is null or equals 0 then search will be performed as less than or equal to max;
     * If max parameter is null or equals 0 then serrch will be performed as greater than or equal to min;
     * </p>
     *
     * @param minimalPopulation of city
     * @param maximumPopulation of city
     * @return list of found cities
     */
    List<City> getByPopulation(Integer minimalPopulation, Integer maximumPopulation);
}
