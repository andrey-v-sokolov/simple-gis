package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.City;
import com.simplegis.webservice.persistence.entity.Organization;
import com.simplegis.webservice.persistence.entity.Street;

import java.math.BigInteger;
import java.util.List;

/**
 * Organization specific data access object interface extension.
 */
public interface OrganizationDao extends GenericDao<Organization>{

    /**
     *
     * @param name
     * @return
     */
    List<Organization> getByName(String name);

    /**
     *
     * @param cityId
     * @return
     */
    List<Organization> getByCityId(BigInteger cityId);

    /**
     *
     * @param cityId
     * @param streetId
     * @return
     */
    List<Organization> getByCityIdAndStreetId(BigInteger cityId, BigInteger streetId);

    /**
     *
     * @param cityId
     * @param streetId
     * @param building
     * @return
     */
    List<Organization> getByCityIdAndStreetIdAndBuilding(BigInteger cityId, BigInteger streetId, Integer building);

    //ToDo: clarify task 3.1 with manager.

    /**
     *
     * @param organizationToken
     * @param geoToken
     * @return
     */
    List<Organization> getByScopeNameOrOrganizationNameAndGeoToken(String organizationToken, String geoToken);

}
