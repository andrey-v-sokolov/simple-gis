package com.simplegis.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * Organization data transfer object.
 */
public class OrganizationDto implements Serializable {

    private static final long serialVersionUID = -880383329;

    private BigInteger id;
    private String name;
    private Integer building;
    private Timestamp modified;
    private String www;
    private BigInteger city;
    private BigInteger street;
    private Integer scope;
    private List<PhoneDto> phones;

    public OrganizationDto() {
    }

    public OrganizationDto(OrganizationDto value) {
        this.id = value.id;
        this.name = value.name;
        this.building = value.building;
        this.modified = value.modified;
        this.www = value.www;
        this.city = value.city;
        this.street = value.street;
        this.scope = value.scope;
        this.phones = value.phones;
    }

    public OrganizationDto(
            BigInteger id,
            String name,
            Integer building,
            Timestamp modified,
            String www,
            BigInteger city,
            BigInteger street,
            Integer scope,
            List<PhoneDto> phones
    ) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.modified = modified;
        this.www = www;
        this.city = city;
        this.street = street;
        this.scope = scope;
        this.phones = phones;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public BigInteger getCity() {
        return city;
    }

    public void setCity(BigInteger city) {
        this.city = city;
    }

    public BigInteger getStreet() {
        return street;
    }

    public void setStreet(BigInteger street) {
        this.street = street;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationDto that = (OrganizationDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(building, that.building)
                .append(modified, that.modified)
                .append(www, that.www)
                .append(city, that.city)
                .append(street, that.street)
                .append(scope, that.scope)
                .append(phones, that.phones)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(building)
                .append(modified)
                .append(www)
                .append(city)
                .append(street)
                .append(scope)
                .append(phones)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("building", building)
                .append("modified", modified)
                .append("www", www)
                .append("city", city)
                .append("street", street)
                .append("scope", scope)
                .append("phones", phones)
                .toString();
    }
}
