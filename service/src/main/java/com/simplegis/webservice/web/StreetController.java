package com.simplegis.webservice.web;

import com.simplegis.common.dto.StreetDto;
import com.simplegis.webservice.persistence.util.mapper.StreetMapper;
import com.simplegis.webservice.service.StreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Street controller class.
 */
@RestController
@RequestMapping("/street")
public class StreetController {
    private static final Logger LOG = LoggerFactory.getLogger(StreetController.class);

    @Autowired
    private StreetService streetService;

    /**
     * Endpoint for getting all streets.
     * @return street dtos List
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<StreetDto> getAll() {
        LOG.info("Received street/getAll request");
        return streetService.getAll()
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for getting specified street.
     * @param id of a specified street
     * @return specified street dto or null if it does not exist
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public StreetDto getById(@PathVariable("id") Long id) {
        LOG.info("Received street/getById/{} request", id);
        return StreetMapper.toDto(streetService.getById(id));
    }

    /**
     * Endpoint for update street.
     * @param streetDto to update
     * @return 1 if street was updated 0 if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody StreetDto streetDto) {
        LOG.info("Received street/update request with body {}", streetDto.toString());
        return streetService.update(StreetMapper.fromDto(streetDto));
    }

    /**
     * Endpoint for insert a street.
     * @param streetDto to insert
     * @return inserted street dto with generated id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public StreetDto insert(@RequestBody StreetDto streetDto) {
        LOG.info("Received street/insert request with body {}", streetDto.toString());
        return StreetMapper.toDto(streetService.insert(StreetMapper.fromDto(streetDto)));
    }

    /**
     * Endpoint for batch street insert.
     * @param streetDtos list to insert
     * @return list of inserted street dtos
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<StreetDto> batchInsert(@RequestBody List<StreetDto> streetDtos) {
        LOG.info("Received street/batchInsert request with list of {} elements", streetDtos.size());

        return streetService.batchInsert(
                streetDtos.stream().map(StreetMapper::fromDto).collect(Collectors.toList())
        ).stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Searches streets by name or name substring.
     * @param name name or substring to search by
     * @return list of found streets dto
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<StreetDto> getByName(@PathVariable("name") String name) {
        LOG.info("Received street/getByName/{} request ", name);

        return streetService.getByName(name).stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search streets by its length.
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets dtos.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByLength/{minLength}/{maxLength}")
    public List<StreetDto> getByLength(
            @PathVariable("minLength") BigDecimal minimalLength,
            @PathVariable("maxLength") BigDecimal maximumLength) {
        LOG.info("Received street/getByLength/{}/{} request ", minimalLength, maximumLength);

        return streetService.getByLength(minimalLength, maximumLength)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search streets by cityId and stret name or street name substring.
     * @param cityId to search in
     * @param name of street or substring to search by
     * @return list of found street dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndName/{cityId}/{name}")
    public List<StreetDto> getByCityIdAndName(
            @PathVariable("cityId") Long cityId,
            @PathVariable("name") String name) {
        LOG.info("Received street/getByCityIdAndName/{}/{} request ", cityId, name);

        return streetService.getByCityIdAndName(cityId, name)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search streets by citId and street length.
     * @param cityId to search in
     * @param minimalLength of street
     * @param maximumLength of street
     * @return list of found streets dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndLength/{cityId}/{minLength}/{maxLength}")
    public List<StreetDto> getByCityIdAndLength(
            @PathVariable("cityId") Long cityId,
            @PathVariable("minLength") BigDecimal minimalLength,
            @PathVariable("maxLength") BigDecimal maximumLength) {
        LOG.info("Received street/getByCityIdAndLength/{}/{}/{} request ", cityId, minimalLength, maximumLength);

        return streetService.getByCityIdAndLength(cityId, minimalLength, maximumLength)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }
}
