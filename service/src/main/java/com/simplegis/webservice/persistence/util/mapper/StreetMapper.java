package com.simplegis.webservice.persistence.util.mapper;

import com.simplegis.common.dto.StreetDto;
import com.simplegis.webservice.persistence.entity.Street;

/**
 * Maps street entity to dto and vice versa.
 */
public final class StreetMapper {

    /**
     * Maps street entity to dto.
     * @param street entity to map
     * @return dto.
     */
    public static StreetDto toDto(Street street) {
        return new StreetDto(
                street.getId(),
                street.getName(),
                street.getLength(),
                street.getCityId(),
                street.getVersion()
        );
    }

    /**
     * Extract street from dto.
     * @param streetDto dto to extract from
     * @return extracted entity
     */
    public static Street fromDto(StreetDto streetDto) {
        return new Street(
                streetDto.getId(),
                streetDto.getName(),
                streetDto.getLength(),
                streetDto.getCityId(),
                streetDto.getVersion()
        );
    }
}
