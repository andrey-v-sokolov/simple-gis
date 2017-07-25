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
     * Service method for getting all streets.
     * @return street List
     */
    public List<Street> getAll() {
        LOG.debug("Getting all streets.");

        return streetDao.getAll();
    }

    /**
     * Service method for getting specified street.
     * @param id of a specified street
     * @return specified street or null if it does not exist
     */
    public Street getById(Long id) {
        return streetDao.getById(id);
    }

    /**
     * Service method for update street.
     * @param street to update
     * @return 1 if street was updated 0 if not
     */
    public Integer update(Street street) {
        return streetDao.update(street);
    }

    /**
     * Service method for insert a street.
     * @param street to insert
     * @return inserted street with generated id
     */
    public Street insert(Street street) {
        return streetDao.insert(street);
    }

    /**
     * Service method for batch street insert.
     * @param streets list to insert
     * @return list of inserted streets
     */
    public List<Street> batchInsert(List<Street> streets) {
        return streetDao.batchInsert(streets);
    }

    /**
     * Searches streets by name or name substring.
     * @param name name or substring to search by
     * @return list of found streets
     */
    public List<Street> getByName(String name) {
        return streetDao.getByName(name);
    }

    /**
     * Service method for search streets by its length.
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets.
     */
    public List<Street> getByLength(BigDecimal minimalLength, BigDecimal maximumLength) {
        return streetDao.getByLength(minimalLength, maximumLength);
    }

    /**
     * Service method for search streets by cityId and stret name or street name substring.
     * @param cityId to search in
     * @param name of street or substring to search by
     * @return list of found street dtos
     */
    public List<Street> getByCityIdAndName(BigInteger cityId, String name) {
        return streetDao.getByCityIdAndName(cityId, name);
    }

    /**
     * Service method for search streets by citId and street length.
     * @param cityId to search in
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets
     */
    public List<Street> getByCityIdAndLength(BigInteger cityId, BigDecimal minimalLength, BigDecimal maximumLength) {
        return streetDao.getByCityIdAndLength(cityId, minimalLength, maximumLength);
    }
}
