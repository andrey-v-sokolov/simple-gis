package com.simplegis.webservice.web;


import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.PhoneDto;
import com.simplegis.common.dto.ScopeDto;
import com.simplegis.webservice.persistence.entity.Organization;
import com.simplegis.webservice.persistence.util.mapper.OrganizationMapper;
import com.simplegis.webservice.persistence.util.mapper.PhoneMapper;
import com.simplegis.webservice.persistence.util.mapper.ScopeMapper;
import com.simplegis.webservice.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Organization controller class.
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * Endpoint for getting all organizations.
     *
     * @return organizations dtos List
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<OrganizationDto> getAll() {
        LOG.info("Received organization/getAll request");

        return organizationService.getAll()
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for getting specified organization.
     *
     * @param id of a specified organization
     * @return specified organization dto or null if it does not exist
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public OrganizationDto getById(@PathVariable("id") Long id) {
        LOG.info("Received organization/getById/{} request", id);

        return OrganizationMapper.toDto(organizationService.getById(id));
    }

    /**
     * Endpoint for update organization.
     *
     * @param organizationDto to update. Phones will not be updated! Use special endpoints to manage phones!
     * @return 1 if organization was updated 0 if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody OrganizationDto organizationDto) {
        LOG.info("Received organization/update request with body {}", organizationDto.toString());

        return organizationService.update(OrganizationMapper.fromDto(organizationDto));
    }

    /**
     * Endpoint for insert an organization.
     *
     * @param organizationDto to insert
     * @return inserted organization dto with generated id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public OrganizationDto insert(@RequestBody OrganizationDto organizationDto) {
        LOG.info("Received organization/insert request with body {}", organizationDto.toString());

        Organization insertedOrg = organizationService.insert(OrganizationMapper.fromDto(organizationDto));
        return OrganizationMapper.toDto(insertedOrg);
    }

    /**
     * Endpoint for batch organizations insert.
     *
     * @param organizationDtos list to insert
     * @return list of inserted organization dtos
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<OrganizationDto> batchInsert(@RequestBody List<OrganizationDto> organizationDtos) {
        LOG.info("Received organization/batchInsert request with list of {} elements", organizationDtos.size());

        return organizationService.batchInsert(
                organizationDtos.stream().map(OrganizationMapper::fromDto).collect(Collectors.toList())
        ).stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found organization dto
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<OrganizationDto> getByName(@PathVariable("name") String name) {
        LOG.info("Received organization/getByName/{} request ", name);

        try {
            name = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return null;
        }

        return organizationService.getByName(name)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by city Id.
     *
     * @param cityId to search in
     * @return list of found organization dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityId/{cityId}")
    public List<OrganizationDto> getByCityId(@PathVariable("cityId") Long cityId) {
        LOG.info("Received organization/getByCityId/{} request ", cityId);

        return organizationService.getByCityId(cityId)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by city and scope ids.
     *
     * @param cityId  to seardh in
     * @param scopeId to search by
     * @return list of found organizations
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndScopeId/{cityId}/{scopeId}")
    public List<OrganizationDto> getByCityIdAndScopeId(
            @PathVariable("cityId") Long cityId,
            @PathVariable("scopeId") Long scopeId) {

        return organizationService.getByCityIdAndScopeId(cityId, scopeId)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by city and street Ids.
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @return list of found organization dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndStreetId/{cityId}/{streetId}")
    public List<OrganizationDto> getByCityIdAndStreetId(
            @PathVariable("cityId") Long cityId,
            @PathVariable("streetId") Long streetId) {
        LOG.info("Received organization/getByCityIdAndStreetId/{}/{} request ", cityId, streetId);

        return organizationService.getByCityIdAndStreetId(cityId, streetId)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by city and street Ids and building ("full address").
     *
     * @param cityId   to search by
     * @param streetId to search by
     * @param building to search by
     * @return list of found organization dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndStreetIdAndBuilding/{cityId}/{streetId}/{building}")
    public List<OrganizationDto> getByCityIdAndStreetIdAndBuilding(
            @PathVariable("cityId") Long cityId,
            @PathVariable("streetId") Long streetId,
            @PathVariable("building") Integer building) {
        LOG.info("Received organization/getByCityIdAndStreetIdAndBuilding/{}/{}/{} request ", cityId, streetId, building);

        return organizationService.getByCityIdAndStreetIdAndBuilding(cityId, streetId, building)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations by string representing a substring of its name or scope
     * and by string representing a substring of city or street name.
     *
     * @param organizationToken string representing a substring of organisation name or scope
     * @param geoToken          string representing a substring of city or street name to search in
     * @return list of found organization dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByScopeNameOrOrganizationNameAndGeoToken/{orgToken}/{geoToken}")
    public List<OrganizationDto> getByScopeNameOrOrganizationNameAndGeoToken(
            @PathVariable("orgToken") String organizationToken,
            @PathVariable("geoToken") String geoToken) {

        try {
            organizationToken = URLDecoder.decode(organizationToken, StandardCharsets.UTF_8.name());
            geoToken = URLDecoder.decode(geoToken, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return null;
        }

        LOG.info("Received organization/getByScopeNameOrOrganizationNameAndGeoToken/{}/{} request ", organizationToken, geoToken);

        return organizationService.getByScopeNameOrOrganizationNameAndGeoToken(organizationToken, geoToken)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search organizations that were added or updated later than specified timestamp.
     *
     * @param timestamp to compare time of modification
     * @return list of found organization dtos
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getOrganizationAddedOrModifiedSince")
    public List<OrganizationDto> getOrganizationAddedOrModifiedSince(@RequestBody Timestamp timestamp) {
        LOG.info("Received organization/getByScopeNameOrOrganizationNameAndGeoToken request with timestamp {}", timestamp.toString());

        return organizationService.getOrganizationAddedOrModifiedSince(timestamp)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for add phone to organization by organization id.
     *
     * @param phoneDto to add
     * @return phone dto with generated id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addPhone")
    public PhoneDto addPhone(@RequestBody PhoneDto phoneDto) {
        LOG.info("Received organization/addPhone request with body {}", phoneDto.toString());

        return PhoneMapper.toDto(organizationService.addPhone(PhoneMapper.fromDto(phoneDto)));
    }

    /**
     * Endpoint for update phone.
     *
     * @param phoneDto to update
     * @return 1 if phone was updated 0 if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/updatePhone")
    public Integer updatePhone(@RequestBody PhoneDto phoneDto) {
        LOG.info("Received organization/updatePhone request with body {}", phoneDto.toString());

        return organizationService.updatePhone(PhoneMapper.fromDto(phoneDto));
    }

    /**
     * Endpoint for delete phone.
     *
     * @param phoneDto to delete
     * @return 1 if phone was deleted 0 if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deletePhone")
    public Integer deletePhone(@RequestBody PhoneDto phoneDto) {
        LOG.info("Received organization/deletePhone request with body {}", phoneDto.toString());

        return organizationService.deletePhone(PhoneMapper.fromDto(phoneDto));
    }

    /**
     * Endpoint for getting all scopes.
     *
     * @return scopes dtos List
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAvailableScopes")
    public List<ScopeDto> getAvailableScopes() {
        LOG.info("Received organization/getAvailableScopes request");

        return organizationService.getAvailableScopes()
                .stream().map(ScopeMapper::toDto).collect(Collectors.toList());
    }
}
