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
     * Get all cities.
     *
     * @return list of all cities.
     */
    public List<CityDto> getAllCities() {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findAllCities());
    }

    /**
     * Find cities by area.
     *
     * @param minArea of a city to find
     * @param maxArea of a city to find
     * @return list of found cities
     */
    public List<CityDto> findCitiesByArea(BigDecimal minArea, BigDecimal maxArea) {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findCitiesByArea(minArea, maxArea));
    }


    /**
     * Find cities by population.
     *
     * @param minPopulation of a city to find
     * @param maxPopulation of a city to find
     * @return list of found cities
     */
    public List<CityDto> findCitiesByPopulation(BigInteger minPopulation, BigInteger maxPopulation) {
        Executor<CityDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findCitiesByPopulation(minPopulation, maxPopulation));
    }

    /**
     * Get all scopes.
     *
     * @return list of all scopes.
     */
    public List<ScopeDto> getAllScopes() {
        Executor<ScopeDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().getAllScopes());
    }

    /**
     * Find a streets with specific length in a defined city.
     *
     * @param cityId to search in
     * @param minLength of a street to find
     * @param maxLength of a street to find
     * @return list of found streets
     */
    public List<StreetDto> findStreetsByCityAndLength(Long cityId, BigDecimal minLength, BigDecimal maxLength) {
        Executor<StreetDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findStreetsByCityAndLength(cityId, minLength, maxLength));
    }

    /**
     * Find organizations by organization and geo tokens.
     *
     * @param orgToken to search in org names scope names or scope keywords
     * @param geoToken to search in city and street names
     * @return list of found organizations
     */
    public List<OrganizationDto> findOrganizationsByStringAndGeoTokens(String orgToken, String geoToken) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByStringAndGeoTokens(orgToken, geoToken));
    }

    /**
     * Find all organizations with defined scope in defined city.
     *
     * @param cityId to search in
     * @param scopeId to search by
     * @return list of found organizations
     */
    public List<OrganizationDto> findOrganizationsByCityAndScopeIds(Long cityId, Long scopeId) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByCityAndScopeIds(cityId, scopeId));
    }

    /**
     * Find organizations in a defined city located on defined street.
     *
     * @param cityId to search in
     * @param streetId to search on
     * @return list of found organizations
     */
    public List<OrganizationDto> findOrganizationsByCityAndStreetIds(Long cityId, Long streetId) {
        Executor<OrganizationDto> executor = new Executor<>();
        return executor.execute(apiService.getClient().findOrganizationsByCityAndStreetIds(cityId, streetId));
    }
}
