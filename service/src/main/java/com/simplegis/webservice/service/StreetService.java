package com.simplegis.webservice.service;

import com.simplegis.webservice.persistence.dao.StreetDao;
import com.simplegis.webservice.persistence.entity.Street;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Street service.
 */
@Service
public class StreetService {
    private static final Logger LOG = LoggerFactory.getLogger(StreetService.class);

    @Autowired
    private StreetDao streetDao;

    /**
     *
     * @return
     */
    public List<Street> getAll() {
        return streetDao.getAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Street getById(BigInteger id) {
        return streetDao.getById(id);
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Street> getByName(String name) {
        return streetDao.getByName(name);
    }

    /**
     *
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    public List<Street> getByLength(BigDecimal minimalLength, BigDecimal maximumLength) {
        return streetDao.getByLength(minimalLength, maximumLength);
    }

    /**
     *
     * @param street
     * @return
     */
    public Integer update(Street street) {
        return streetDao.update(street);
    }

    /**
     *
     * @param cityId
     * @param name
     * @return
     */
    public List<Street> getByCityIdAndName(BigInteger cityId, String name) {
        return streetDao.getByCityIdAndName(cityId, name);
    }

    /**
     *
     * @param cityId
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    public List<Street> getByCityIdAndLength(BigInteger cityId, BigDecimal minimalLength, BigDecimal maximumLength) {
        return streetDao.getByCityIdAndLength(cityId, minimalLength, maximumLength);
    }

    /**
     *
     * @param street
     * @return
     */
    public Street insert(Street street) {
        return streetDao.insert(street);
    }

    /**
     *
     * @param streets
     * @return
     */
    public List<Street> batchInsert(List<Street> streets) {
        return streetDao.batchInsert(streets);
    }
}
