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
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {
    private static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<CityDto> getAll() {
        return cityService.getAll()
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public CityDto getById(@PathVariable("id") BigInteger id) {
        return CityMapper.toDto(cityService.getById(id));
    }

    /**
     *
     * @param cityDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody CityDto cityDto) {
        return cityService.update(CityMapper.fromDto(cityDto));
    }

    /**
     *
     * @param cityDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public CityDto insert(@RequestBody CityDto cityDto) {
        return CityMapper.toDto(cityService.insert(CityMapper.fromDto(cityDto)));
    }

    /**
     *
     * @param cityDtos
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<CityDto> batchInsert(@RequestBody List<CityDto> cityDtos) {
        return cityService.batchInsert(
                cityDtos.stream().map(CityMapper::fromDto).collect(Collectors.toList())
        ).stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<CityDto> getByName(@PathVariable("name") String name) {
        return cityService.getByName(name).stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param minimalArea
     * @param maximumArea
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByArea/{minArea}/{maxArea}")
    public List<CityDto> getByArea(@PathVariable("minArea") BigDecimal minimalArea,
                                   @PathVariable("maxArea") BigDecimal maximumArea) {
        return cityService.getByArea(minimalArea, maximumArea)
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param minimalPopulation
     * @param maximumPopulation
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByPopulation/{minPopulation}/{maxPopulation}")
    public List<CityDto> getByPopulation(@PathVariable("minPopulation") Integer minimalPopulation,
                                         @PathVariable("maxPopulation") Integer maximumPopulation) {
        return cityService.getByPopulation(minimalPopulation, maximumPopulation)
                .stream().map(CityMapper::toDto).collect(Collectors.toList());
    }
}
