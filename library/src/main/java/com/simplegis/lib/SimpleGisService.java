package com.simplegis.lib;

import com.simplegis.common.dto.CityDto;
import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.ScopeDto;
import com.simplegis.common.dto.StreetDto;
import com.simplegis.lib.api.ApiService;
import com.simplegis.lib.api.util.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Service to interact with simple gis webservice.
 */
@Service
public class SimpleGisService {

    @Autowired
    private ApiService apiService;

    /**
     *
     * @return
     */
    public List<CityDto> getAllCities() {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findAllCities());
    }

    /**
     *
     * @param minArea
     * @param maxArea
     * @return
     */
    public List<CityDto> findCitiesByArea(BigDecimal minArea, BigDecimal maxArea) {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findCitiesByArea(minArea, maxArea));
    }


    /**
     *
     * @param minPopulation
     * @param maxPopulation
     * @return
     */
    public List<CityDto> findCitiesByPopulation(BigInteger minPopulation, BigInteger maxPopulation) {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findCitiesByPopulation(minPopulation, maxPopulation));
    }

    /**
     *
     * @return
     */
    public List<ScopeDto> getAllScopes() {
        Executor<ScopeDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().getAllScopes());
    }

    /**
     *
     * @param cityId
     * @param minLength
     * @param maxLength
     * @return
     */
    public List<StreetDto> findStreetsByCityAndLength(Long cityId, BigDecimal minLength, BigDecimal maxLength) {
        Executor<StreetDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findStreetsByCityAndLength(cityId, minLength, maxLength));
    }

    /**
     *
     * @param orgToken
     * @param geoToken
     * @return
     */
    public List<OrganizationDto> findOrganizationsByStringAndGeoTokens(String orgToken, String geoToken) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByStringAndGeoTokens(orgToken, geoToken));
    }

    /**
     *
     * @param cityId
     * @param scopeId
     * @return
     */
    public List<OrganizationDto> findOrganizationsByCityAndScopeIds(Long cityId, Long scopeId) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByCityAndScopeIds(cityId, scopeId));
    }

    /**
     *
     * @param cityId
     * @param streetId
     * @return
     */
    public List<OrganizationDto> findOrganizationsByCityAndStreetIds(Long cityId, Long streetId) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByCityAndStreetIds(cityId, streetId));
    }
}
