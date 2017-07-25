package com.simplegis.webservice.web;

import com.simplegis.common.dto.CityDto;
import com.simplegis.webservice.persistence.util.mapper.CityMapper;
import com.simplegis.webservice.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * City controller class.
 */
@RestController
@RequestMapping("/city")
public class CityController {
    private static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    /**
     * Endpoint for getting all cities.
     *
     * @return city dtos List
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<CityDto> getAll() {
        LOG.info("Received city/getAll request");

        return cityService.getAll()
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for getting specified city.
     *
     * @param id of a specified city
     * @return specified city dto or null if it does not exist
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public CityDto getById(@PathVariable("id") BigInteger id) {
        LOG.info("Received city/getById/{} request", id);

        return CityMapper.toDto(cityService.getById(id));
    }

    /**
     * Endpoint for update city.
     *
     * @param cityDto to update
     * @return 1 if city was updated 0 if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody CityDto cityDto) {
        LOG.info("Received city/update request with body {}", cityDto.toString());

        return cityService.update(CityMapper.fromDto(cityDto));
    }

    /**
     * Endpoint for insert a city.
     *
     * @param cityDto to insert
     * @return inserted city dto with generated id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public CityDto insert(@RequestBody CityDto cityDto) {
        LOG.info("Received city/insert request with body {}", cityDto.toString());

        return CityMapper.toDto(cityService.insert(CityMapper.fromDto(cityDto)));
    }

    /**
     * Endpoint for batch cities insert.
     *
     * @param cityDtos list to insert
     * @return list of inserted city dtos
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<CityDto> batchInsert(@RequestBody List<CityDto> cityDtos) {
        LOG.info("Received city/batchInsert request with list of {} elements", cityDtos.size());

        return cityService.batchInsert(
                cityDtos.stream().map(CityMapper::fromDto).collect(Collectors.toList())
        ).stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search cities by name or name substring.
     *
     * @param name name or substring to search by
     * @return list of found cities dto
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<CityDto> getByName(@PathVariable("name") String name) {
        LOG.info("Received city/getByName/{} request ", name);

        return cityService.getByName(name).stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search cities by its area.
     *
     * @param minimalArea of city
     * @param maximumArea of city
     * @return list of found city dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByArea/{minArea}/{maxArea}")
    public List<CityDto> getByArea(@PathVariable("minArea") BigDecimal minimalArea,
                                   @PathVariable("maxArea") BigDecimal maximumArea) {
        LOG.info("Received city/getByArea/{}/{} request ", minimalArea, maximumArea);

        return cityService.getByArea(minimalArea, maximumArea)
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Endpoint for search cities by its population.
     *
     * @param minimalPopulation of city
     * @param maximumPopulation of city
     * @return list of found city dtos
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByPopulation/{minPopulation}/{maxPopulation}")
    public List<CityDto> getByPopulation(@PathVariable("minPopulation") Integer minimalPopulation,
                                         @PathVariable("maxPopulation") Integer maximumPopulation) {
        LOG.info("Received city/getByPopulation/{}/{} request ", minimalPopulation, maximumPopulation);

        return cityService.getByPopulation(minimalPopulation, maximumPopulation)
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }
}
