package com.simplegis.webservice.web;


import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.PhoneDto;
import com.simplegis.common.dto.ScopeDto;
import com.simplegis.webservice.persistence.util.mapper.OrganizationMapper;
import com.simplegis.webservice.persistence.util.mapper.PhoneMapper;
import com.simplegis.webservice.persistence.util.mapper.ScopeMapper;
import com.simplegis.webservice.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;


    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<OrganizationDto> getAll() {
        return organizationService.getAll()
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public OrganizationDto getById(@PathVariable("id") BigInteger id) {
        return OrganizationMapper.toDto(organizationService.getById(id));
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<OrganizationDto> getByName(@PathVariable("name") String name) {
        return organizationService.getByName(name)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organization
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody OrganizationDto organization) {
        return organizationService.update(OrganizationMapper.fromDto(organization));
    }

    /**
     *
     * @param cityId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityId/{cityId}")
    public List<OrganizationDto> getByCityId(@PathVariable("cityId") BigInteger cityId) {
        return organizationService.getByCityId(cityId)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organization
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public OrganizationDto insert(@RequestBody OrganizationDto organization) {
        return OrganizationMapper.toDto(organizationService.insert(OrganizationMapper.fromDto(organization)));
    }

    /**
     *
     * @param cityId
     * @param streetId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndStreetId/cityId/streetId")
    public List<OrganizationDto> getByCityIdAndStreetId(
            @PathVariable("cityId") BigInteger cityId,
            @PathVariable("streetId") BigInteger streetId) {
        return organizationService.getByCityIdAndStreetId(cityId, streetId)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param cityId
     * @param streetId
     * @param building
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndStreetIdAndBuilding/{cityId}/{streetId}/{building}")
    public List<OrganizationDto> getByCityIdAndStreetIdAndBuilding(
            @PathVariable("cityId") BigInteger cityId,
            @PathVariable("streetId") BigInteger streetId,
            @PathVariable("building") Integer building) {
        return organizationService.getByCityIdAndStreetIdAndBuilding(cityId, streetId, building)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organizations
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<OrganizationDto> batchInsert(@RequestBody List<OrganizationDto> organizations) {
        return organizationService.batchInsert(
                organizations.stream().map(OrganizationMapper::fromDto).collect(Collectors.toList())
        ).stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organizationToken
     * @param geoToken
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByScopeNameOrOrganizationNameAndGeoToken/{orgToken}/{geoToken}")
    public List<OrganizationDto> getByScopeNameOrOrganizationNameAndGeoToken(
            @PathVariable("orgToken") String organizationToken,
            @PathVariable("geoToken") String geoToken) {
        return organizationService.getByScopeNameOrOrganizationNameAndGeoToken(organizationToken, geoToken)
                .stream().map(OrganizationMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param orgId
     * @param phoneDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{orgId}/addPhone")
    public PhoneDto addPhone(
            @PathVariable(value = "orgId") BigInteger orgId,
            @RequestBody PhoneDto phoneDto) {
        phoneDto.setOrganizationId(orgId);
        return PhoneMapper.toDto(organizationService.addPhone(PhoneMapper.fromDto(phoneDto)));
    }

    /**
     *
     * @param orgId
     * @param phoneDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{orgId}/updatePhone")
    public Integer updatePhone(
            @PathVariable(value = "orgId") BigInteger orgId,
            @RequestBody PhoneDto phoneDto) {
        phoneDto.setOrganizationId(orgId);
        return organizationService.updatePhone(PhoneMapper.fromDto(phoneDto));
    }

    /**
     *
     * @param orgId
     * @param phoneDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{orgId}/deletePhone")
    public Integer deletePhone(
            @PathVariable(value = "orgId") BigInteger orgId,
            @RequestBody PhoneDto phoneDto) {
        phoneDto.setOrganizationId(orgId);
        return organizationService.deletePhone(PhoneMapper.fromDto(phoneDto));
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAvailableScopes")
    public List<ScopeDto> getAvailableScopes() {
        return organizationService.getAvailableScopes()
                .stream().map(ScopeMapper::toDto).collect(Collectors.toList());
    }
}
