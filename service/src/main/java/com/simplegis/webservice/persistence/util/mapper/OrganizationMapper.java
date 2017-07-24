package com.simplegis.webservice.persistence.util.mapper;

import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.webservice.persistence.entity.Organization;
import com.simplegis.webservice.persistence.entity.Phone;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps organization entity to dto and vice versa.
 */
public final class OrganizationMapper {

    /**
     * Maps organization entity to dto.
     * @param organization entity to map
     * @param phones belongs to entity
     * @return dto.
     */
    public static OrganizationDto toDto(Organization organization, List<Phone> phones) {
        return new OrganizationDto(
                organization.getId(),
                organization.getName(),
                organization.getBuilding(),
                organization.getModified(),
                organization.getWww(),
                organization.getCity(),
                organization.getStreet(),
                organization.getScope(),
                phones.stream().map(PhoneMapper::toDto).collect(Collectors.toList())
        );
    }

    /**
     * Extract organization from dto.
     * @param organizationDto dto to extract from
     * @return extracted entity
     */
    public static Organization fromDto(OrganizationDto organizationDto) {
        return new Organization(
                organizationDto.getId(),
                organizationDto.getName(),
                organizationDto.getBuilding(),
                organizationDto.getModified(),
                organizationDto.getWww(),
                organizationDto.getCity(),
                organizationDto.getStreet(),
                organizationDto.getScope()
        );
    }

    /**
     * Extract organization phones from organization dto.
     * @param organizationDto dto to extract from
     * @return list of extracted phones
     */
    public static List<Phone> extractPhones(OrganizationDto organizationDto) {
        return organizationDto.getPhones().stream().map(PhoneMapper::fromDto).collect(Collectors.toList());
    }
}
