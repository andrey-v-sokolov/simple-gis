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
import org.springframework.transaction.annotation.Transactional;

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
     * Service method for getting all organizations.
     *
     * @return organizations List
     */
    public List<Organization> getAll() {
        LOG.debug("Getting all organizations.");

        return organizationDao.getAll();
    }

    /**
     * Service method for getting specified organization.
     *
     * @param id of a specified organization
     * @return specified organization or null if it does not exist
     */
    public Organization getById(Long id) {
        return organizationDao.getById(id);
    }

    /**
     * Service method for update organization.
     *
     * @param organization to update. Phones will not be updated! Use special Service methods to manage phones!
     * @return 1 if organization was updated 0 if not
     */
    public Integer update(Organization organization) {
        return organizationDao.update(organization);
    }

    /**
     * Service method for insert an organization.
     *
     * @param organization to insert
     * @return inserted organization with generated id
     */
    @Transactional
    public Organization insert(Organization organization) {

        return organizationDao.insert(organization);
    }

    /**
     * Service method for batch organizations insert.
     *
     * @param organizations list to insert
     * @return list of inserted organizations
     */
    @Transactional
    public List<Organization> batchInsert(List<Organization> organizations) {

        return organizationDao.batchInsert(organizations);
    }

    /**
     * Service method for search organizations by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found organizations
     */
    public List<Organization> getByName(String name) {
        return organizationDao.getByName(name);
    }

    /**
     * Service method for search organizations by city Id.
     *
     * @param cityId to search in
     * @return list of found organizations
     */
    public List<Organization> getByCityId(Long cityId) {
        return organizationDao.getByCityId(cityId);
    }

    /**
     * Service method for search organizations by city and scope ids.
     *
     * @param cityId to seardh in
     * @param scopeId to search by
     * @return list of found organizations
     */
    public List<Organization> getByCityIdAndScopeId(Long cityId, Long scopeId) {
        return organizationDao.getByCityIdAndScopeId(cityId, scopeId);
    }

    /**
     * Service method for search organizations by city and street Ids.
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @return list of found organizations
     */
    public List<Organization> getByCityIdAndStreetId(Long cityId, Long streetId) {
        return organizationDao.getByCityIdAndStreetId(cityId, streetId);
    }

    /**
     * Service method for search organizations by city and street Ids and building ("full address").
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @param building to search by
     * @return list of found organizations
     */
    public List<Organization> getByCityIdAndStreetIdAndBuilding(Long cityId, Long streetId, Integer building) {
        return organizationDao.getByCityIdAndStreetIdAndBuilding(cityId, streetId, building);
    }

    /**
     * Service method for search organizations by string representing a substring of its name or scope
     * and by string representing a substring of city or street name.
     *
     * @param organizationToken string representing a substring of organisation name or scope
     * @param geoToken          string representing a substring of city or street name to search in
     * @return list of found organizations
     */
    public List<Organization> getByScopeNameOrOrganizationNameAndGeoToken(String organizationToken, String geoToken) {
        return organizationDao.getByScopeNameOrOrganizationNameAndGeoToken(organizationToken, geoToken);
    }

    /**
     * Service method for search organizations that were added or updated later than specified timestamp.
     *
     * @param timestamp to compare time of modification
     * @return list of found organizations
     */
    public List<Organization> getOrganizationAddedOrModifiedSince(Timestamp timestamp) {
        return organizationDao.getOrganizationAddedOrModifiedSince(timestamp);
    }


    /**
     * Service method for add phone to organization by organization id.
     *
     * @param phone to add
     * @return phone with generated id
     */
    public Phone addPhone(Phone phone) {
        return phoneDao.insert(phone);
    }

    /**
     * Service method for update phone.
     *
     * @param phone to update
     * @return 1 if phone was updated 0 if not
     */
    public Integer updatePhone(Phone phone) {
        return phoneDao.update(phone);
    }

    /**
     * Service method for delete phone.
     *
     * @param phone to delete
     * @return 1 if phone was deleted 0 if not
     */
    public Integer deletePhone(Phone phone) {
        return phoneDao.delete(phone);
    }

    /**
     * Service method for getting all scopes.
     *
     * @return scope List
     */
    public List<Scope> getAvailableScopes() {
        return scopeDao.getAll();
    }

    /**
     * Service method for inserting a list of phones as batch.
     *
     * @param phones to insert
     * @return phone list with generated ids
     */
    public List<Phone> addPhoneList(List<Phone> phones) {
        return phoneDao.batchInsert(phones);
    }

    /**
     * Service method for getting organization phones.
     *
     * @param orgId to get phones for
     * @return list of organization phones.
     */
    public List<Phone> getPhonesByOrganizationId(Long orgId) {
        return phoneDao.getByOrganizationId(orgId);
    }

}
