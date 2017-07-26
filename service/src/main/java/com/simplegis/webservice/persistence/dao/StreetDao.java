package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.Street;

import java.math.BigDecimal;
import java.util.List;

/**
 * Street specific data access object interface extension.
 */
public interface StreetDao extends GenericDao<Street> {

    /**
     * DAO method by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found streets
     */
    List<Street> getByName(String name);

    /**
     * Service method for search streets by its length.
     * <p>
     * Min and max parameters are inclusive;
     * If min and max parameters are equal then strict value will be searched;
     * If min parameter is null or equals 0 then search will be performed as less than or equal to max;
     * If max parameter is null or equals 0 then serrch will be performed as greater than or equal to min;
     * </p>
     *
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets.
     */
    List<Street> getByLength(BigDecimal minimalLength, BigDecimal maximumLength);

    /**
     * Service method for search streets by cityId and stret name or street name substring.
     *
     * @param cityId to search in
     * @param name   of street or substring to search by
     * @return list of found street dtos
     */
    List<Street> getByCityIdAndName(Long cityId, String name);

    /**
     * Service method for search streets by citId and street length.
     * <p>
     * Min and max parameters are inclusive;
     * If min and max parameters are equal then strict value will be searched;
     * If min parameter is null or equals 0 then search will be performed as less than or equal to max;
     * If max parameter is null or equals 0 then serrch will be performed as greater than or equal to min;
     * </p>
     *
     * @param cityId        to search in
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets
     */
    List<Street> getByCityIdAndLength(Long cityId, BigDecimal minimalLength, BigDecimal maximumLength);
}
