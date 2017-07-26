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
     *
     * @return
     */
    @GET("city/getAll")
    Call<List<CityDto>> findAllCities();

    /**
     *
     * @param minArea
     * @param maxArea
     * @return
     */
    @GET("city/getByArea/{minArea}/{maxArea}/")
    Call<List<CityDto>> findCitiesByArea(
            @Path("minArea") BigDecimal minArea,
            @Path("maxArea") BigDecimal maxArea);

    /**
     *
     * @param minPopulation
     * @param maxPopulation
     * @return
     */
    @GET("city/getByPopulation/{minPopulation}/{maxPopulation}/")
    Call<List<CityDto>> findCitiesByPopulation(
            @Path("minPopulation") BigInteger minPopulation,
            @Path("maxPopulation") BigInteger maxPopulation);

    /**
     *
     * @return
     */
    @GET("organization/getAvailableScopes")
    Call<List<ScopeDto>> getAllScopes();

    /**
     *
     * @param cityId
     * @param minLength
     * @param maxLength
     * @return
     */
    @GET("street/getByCityIdAndLength/{cityId}/{minLength}/{maxLength}/")
    Call<List<StreetDto>> findStreetsByCityAndLength(
            @Path("cityId") Long cityId,
            @Path("minLength") BigDecimal minLength,
            @Path("maxLength") BigDecimal maxLength);

    /**
     *
     * @param orgToken
     * @param geoToken
     * @return
     */
    @GET("organization/getByScopeNameOrOrganizationNameAndGeoToken/{orgToken}/{geoToken}")
    Call<List<OrganizationDto>> findOrganizationsByStringAndGeoTokens(
            @Path("orgToken") String orgToken,
            @Path("geoToken") String geoToken);

    /**
     *
     * @param cityId
     * @param scopeId
     * @return
     */
    @GET("organization/getByCityIdAndScopeId/{cityId}/{scopeId}")
    Call<List<OrganizationDto>> findOrganizationsByCityAndScopeIds(
            @Path("cityId") Long cityId,
            @Path("scopeId") Long scopeId);

    /**
     *
     * @param cityId
     * @param streetId
     * @return
     */
    @GET("organization/getByCityIdAndStreetId/{cityId}/{streetId}")
    Call<List<OrganizationDto>> findOrganizationsByCityAndStreetIds(
            @Path("cityId") Long cityId,
            @Path("streetId") Long streetId);
}
