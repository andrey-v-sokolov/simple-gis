package com.simplegis.webservice.service;

import com.simplegis.webservice.persistence.dao.OrganizationDao;
import com.simplegis.webservice.persistence.dao.PhoneDao;
import com.simplegis.webservice.persistence.dao.ScopeDao;
import com.simplegis.webservice.persistence.entity.Organization;
import com.simplegis.webservice.persistence.entity.Phone;
import com.simplegis.webservice.persistence.entity.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * Organization service.
 */
@Service
public class OrganizationService {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private PhoneDao phoneDao;
    @Autowired
    private ScopeDao scopeDao;

    /**
     *
     * @return
     */
    public List<Organization> getAll() {
        return organizationDao.getAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Organization getById(Long id) {
        return organizationDao.getById(id);
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Organization> getByName(String name) {
        return organizationDao.getByName(name);
    }

    /**
     *
     * @param organization
     * @return
     */
    public Integer update(Organization organization) {
        return organizationDao.update(organization);
    }

    /**
     *
     * @param cityId
     * @return
     */
    public List<Organization> getByCityId(BigInteger cityId) {
        return organizationDao.getByCityId(cityId);
    }

    /**
     *
     * @param organization
     * @return
     */
    public Organization insert(Organization organization) {
        return organizationDao.insert(organization);
    }

    /**
     *
     * @param cityId
     * @param streetId
     * @return
     */
    public List<Organization> getByCityIdAndStreetId(BigInteger cityId, BigInteger streetId) {
        return organizationDao.getByCityIdAndStreetId(cityId, streetId);
    }

    /**
     *
     * @param cityId
     * @param streetId
     * @param building
     * @return
     */
    public List<Organization> getByCityIdAndStreetIdAndBuilding(BigInteger cityId, BigInteger streetId, Integer building) {
        return organizationDao.getByCityIdAndStreetIdAndBuilding(cityId, streetId, building);
    }

    /**
     *
     * @param organizations
     * @return
     */
    public List<Organization> batchInsert(List<Organization> organizations) {
        return organizationDao.batchInsert(organizations);
    }

    /**
     *
     * @param organizationToken
     * @param geoToken
     * @return
     */
    public List<Organization> getByScopeNameOrOrganizationNameAndGeoToken(String organizationToken, String geoToken) {
        return organizationDao.getByScopeNameOrOrganizationNameAndGeoToken(organizationToken, geoToken);
    }

    /**
     *
     * @param timestamp
     * @return
     */
    public List<Organization> getOrganizationAddedOrModifiedSince(Timestamp timestamp) {
        return organizationDao.getOrganizationAddedOrModifiedSince(timestamp);
    }

    /**
     *
     * @param orgId
     * @return
     */
    public List<Phone> getPhonesByOrganizationId(Long orgId) {
        return phoneDao.getByOrganizationId(orgId);
    }

    /**
     *
     * @param phone
     * @return
     */
    public Phone addPhone(Phone phone) {
        return phoneDao.insert(phone);
    }

    /**
     *
     * @param phones
     * @return
     */
    public List<Phone> addPhoneList(List<Phone> phones) {
        return phoneDao.batchInsert(phones);
    }

    /**
     *
     * @param phone
     * @return
     */
    public Integer updatePhone(Phone phone) {
        return phoneDao.update(phone);
    }

    /**
     *
     * @param phone
     * @return
     */
    public Integer deletePhone(Phone phone) {
        return phoneDao.delete(phone);
    }

    /**
     *
     * @return
     */
    public List<Scope> getAvailableScopes() {
        return scopeDao.getAll();
    }

}
