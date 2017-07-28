package com.simplegis.lib.api;

import com.simplegis.common.dto.CityDto;
import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.ScopeDto;
import com.simplegis.common.dto.StreetDto;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Simplegis webservice client.
 */
@Component
public interface SimpleGisApiClient {


    /**
     * Call service to get all cities.
     *
     * @return list of all cities.
     */
    @GET("city/getAll")
    Call<List<CityDto>> findAllCities();

    /**
     * Call service to find cities by area.
     *
     * @param minArea of a city to find
     * @param maxArea of a city to find
     * @return list of found cities
     */
    @GET("city/getByArea/{minArea}/{maxArea}/")
    Call<List<CityDto>> findCitiesByArea(
            @Path("minArea") BigDecimal minArea,
            @Path("maxArea") BigDecimal maxArea);

    /**
     * Call service to find cities by population.
     *
     * @param minPopulation of a city to find
     * @param maxPopulation of a city to find
     * @return list of found cities
     */
    @GET("city/getByPopulation/{minPopulation}/{maxPopulation}/")
    Call<List<CityDto>> findCitiesByPopulation(
            @Path("minPopulation") BigInteger minPopulation,
            @Path("maxPopulation") BigInteger maxPopulation);

    /**
     * Call service to get all scopes.
     *
     * @return list of all scopes.
     */
    @GET("organization/getAvailableScopes")
    Call<List<ScopeDto>> getAllScopes();

    /**
     * Call service to find a streets with specific length in a defined city.
     *
     * @param cityId to search in
     * @param minLength of a street to find
     * @param maxLength of a street to find
     * @return list of found streets
     */
    @GET("street/getByCityIdAndLength/{cityId}/{minLength}/{maxLength}/")
    Call<List<StreetDto>> findStreetsByCityAndLength(
            @Path("cityId") Long cityId,
            @Path("minLength") BigDecimal minLength,
            @Path("maxLength") BigDecimal maxLength);

    /**
     * Call service to find organizations by organization and geo tokens.
     *
     * @param orgToken to search in org names scope names or scope keywords
     * @param geoToken to search in city and street names
     * @return list of found organizations
     */
    @GET("organization/getByScopeNameOrOrganizationNameAndGeoToken/{orgToken}/{geoToken}")
    Call<List<OrganizationDto>> findOrganizationsByStringAndGeoTokens(
            @Path(value = "orgToken", encoded = true) String orgToken,
            @Path(value = "geoToken", encoded = true) String geoToken);

    /**
     * Call service to find all organizations with defined scope in defined city.
     *
     * @param cityId to search in
     * @param scopeId to search by
     * @return list of found organizations
     */
    @GET("organization/getByCityIdAndScopeId/{cityId}/{scopeId}")
    Call<List<OrganizationDto>> findOrganizationsByCityAndScopeIds(
            @Path("cityId") Long cityId,
            @Path("scopeId") Long scopeId);

    /**
     * Call service to find organizations in a defined city located on defined street.
     *
     * @param cityId to search in
     * @param streetId to search on
     * @return list of found organizations
     */
    @GET("organization/getByCityIdAndStreetId/{cityId}/{streetId}")
    Call<List<OrganizationDto>> findOrganizationsByCityAndStreetIds(
            @Path("cityId") Long cityId,
            @Path("streetId") Long streetId);
}
