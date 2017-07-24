package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.Street;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Street specific data access object interface extension.
 */
public interface StreetDao extends GenericDao<Street> {

    /**
     *
     * @param name
     * @return
     */
    List<Street> getByName(String name);

    /**
     *
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    List<Street> getByLength(BigDecimal minimalLength, BigDecimal maximumLength);

    /**
     *
     * @param cityId
     * @param name
     * @return
     */
    List<Street> getByCityIdAndName(BigInteger cityId, String name);

    /**
     *
     * @param cityId
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    List<Street> getByCityIdAndLength(BigInteger cityId, BigDecimal minimalLength, BigDecimal maximumLength);
}
