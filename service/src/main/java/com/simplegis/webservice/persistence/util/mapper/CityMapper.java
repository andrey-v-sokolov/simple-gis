package com.simplegis.webservice.persistence.util.mapper;

import com.simplegis.common.dto.CityDto;
import com.simplegis.webservice.persistence.entity.City;

/**
 * Maps city entity to dto and vice versa.
 */
public final class CityMapper {

    /**
     * Maps city entity to dto.
     * @param city entity to map
     * @return dto.
     */
    public static CityDto toDto(City city) {
        return new CityDto(
                city.getId(),
                city.getName(),
                city.getArea(),
                city.getPopulation(),
                city.getVersion()
        );
    }

    /**
     * Extract city from dto.
     * @param cityDto dto to extract from
     * @return extracted entity
     */
    public static City fromDto(CityDto cityDto) {
        return new City(
                cityDto.getId(),
                cityDto.getName(),
                cityDto.getArea(),
                cityDto.getPopulation(),
                cityDto.getVersion()
        );
    }
}
