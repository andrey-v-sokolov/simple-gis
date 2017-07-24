package com.simplegis.webservice.web;

import com.simplegis.common.dto.StreetDto;
import com.simplegis.webservice.persistence.util.mapper.StreetMapper;
import com.simplegis.webservice.service.StreetService;
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
@RequestMapping("/street")
public class StreetController {
    private static final Logger LOG = LoggerFactory.getLogger(StreetController.class);

    @Autowired
    private StreetService streetService;

    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<StreetDto> getAll() {
        return streetService.getAll()
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public StreetDto getById(@PathVariable("id") BigInteger id) {
        return StreetMapper.toDto(streetService.getById(id));
    }

    /**
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<StreetDto> getByName(@PathVariable("name") String name) {
        return streetService.getByName(name).stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByLength/{minLength}/{maxLength}")
    public List<StreetDto> getByLength(
            @PathVariable("minLength") BigDecimal minimalLength,
            @PathVariable("maxLength") BigDecimal maximumLength) {
        return streetService.getByLength(minimalLength, maximumLength)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param streetDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Integer update(@RequestBody StreetDto streetDto) {
        return streetService.update(StreetMapper.fromDto(streetDto));
    }

    /**
     * @param cityId
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndName/{cityId}/{name}")
    public List<StreetDto> getByCityIdAndName(
            @PathVariable("cityID") BigInteger cityId,
            @PathVariable("name") String name) {
        return streetService.getByCityIdAndName(cityId, name)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param cityId
     * @param minimalLength
     * @param maximumLength
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getByCityIdAndLength/{cityId}/{minLength}/{maxLength}")
    public List<StreetDto> getByCityIdAndLength(
            @PathVariable("cityId") BigInteger cityId,
            @PathVariable("minLength") BigDecimal minimalLength,
            @PathVariable("maxLength") BigDecimal maximumLength) {
        return streetService.getByCityIdAndLength(cityId, minimalLength, maximumLength)
                .stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param streetDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public StreetDto insert(@RequestBody StreetDto streetDto) {
        return StreetMapper.toDto(streetService.insert(StreetMapper.fromDto(streetDto)));
    }

    /**
     * @param streetDtos
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchInsert")
    public List<StreetDto> batchInsert(@RequestBody List<StreetDto> streetDtos) {
        return streetService.batchInsert(
                streetDtos.stream().map(StreetMapper::fromDto).collect(Collectors.toList())
        ).stream().map(StreetMapper::toDto).collect(Collectors.toList());
    }
}
