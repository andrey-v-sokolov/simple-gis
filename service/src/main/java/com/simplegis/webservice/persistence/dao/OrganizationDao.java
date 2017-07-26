package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.Organization;

import java.sql.Timestamp;
import java.util.List;

/**
 * Organization specific data access object interface extension.
 */
public interface OrganizationDao extends GenericDao<Organization> {

    /**
     * DAO method for search organizations by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found organizations
     */
    List<Organization> getByName(String name);

    /**
     * DAO method for search organizations by city Id.
     *
     * @param cityId to search in
     * @return list of found organizations
     */
    List<Organization> getByCityId(Long cityId);

    /**
     * DAO method for search organizations by city and street Ids.
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @return list of found organizations
     */
    List<Organization> getByCityIdAndStreetId(Long cityId, Long streetId);

    /**
     * DAO method for search organizations by city and street Ids and building ("full address").
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @param building to search by
     * @return list of found organizations
     */
    List<Organization> getByCityIdAndStreetIdAndBuilding(Long cityId, Long streetId, Integer building);

    /**
     * DAO method for search organizations by string representing a substring of its name or scope
     * and by string representing a substring of city or street name.
     *
     * @param organizationToken string representing a substring of organisation name or scope
     * @param geoToken          string representing a substring of city or street name to search in
     * @return list of found organizations
     */
    List<Organization> getByScopeNameOrOrganizationNameAndGeoToken(String organizationToken, String geoToken);

    /**
     * DAO method for search organizations that were added or updated later than specified timestamp.
     *
     * @param timestamp to compare time of modification
     * @return list of found organizations
     */
    List<Organization> getOrganizationAddedOrModifiedSince(Timestamp timestamp);

}
