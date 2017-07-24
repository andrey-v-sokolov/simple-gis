package com.simplegis.webservice.persistence.util.mapper;

import com.simplegis.common.dto.PhoneDto;
import com.simplegis.webservice.persistence.entity.Phone;

/**
 * Maps phone entity to dto and vice versa.
 */
public final class PhoneMapper {

    /**
     * Maps phone entity to dto.
     * @param phone entity to map
     * @return dto.
     */
    public static PhoneDto toDto(Phone phone) {
        return new PhoneDto(
                phone.getId(),
                phone.getNumber(),
                phone.getOrganizationId(),
                phone.getVersion()
        );
    }

    /**
     * Extract phone from dto.
     * @param phoneDto dto to extract from
     * @return extracted entity
     */
    public static Phone fromDto(PhoneDto phoneDto) {
        return new Phone(
                phoneDto.getId(),
                phoneDto.getNumber(),
                phoneDto.getOrganizationId(),
                phoneDto.getVersion()
        );
    }
}
