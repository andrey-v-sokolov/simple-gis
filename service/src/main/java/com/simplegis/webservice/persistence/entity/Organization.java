package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Organization entity.
 */
public class Organization implements Serializable {

    private static final long serialVersionUID = -880383328;

    private Long id;
    private String name;
    private Integer building;
    private Timestamp modified;
    private String www;
    private Long city;
    private Long street;
    private Integer scope;

    public Organization() {
    }

    public Organization(Organization value) {
        this.id = value.id;
        this.name = value.name;
        this.building = value.building;
        this.modified = value.modified;
        this.www = value.www;
        this.city = value.city;
        this.street = value.street;
        this.scope = value.scope;
    }

    public Organization(
            Long id,
            String name,
            Integer building,
            Timestamp modified,
            String www,
            Long city,
            Long street,
            Integer scope
    ) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.modified = modified;
        this.www = www;
        this.city = city;
        this.street = street;
        this.scope = scope;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getStreet() {
        return street;
    }

    public void setStreet(Long street) {
        this.street = street;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Organization that = (Organization) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(building, that.building)
                .append(modified, that.modified)
                .append(www, that.www)
                .append(city, that.city)
                .append(street, that.street)
                .append(scope, that.scope)
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
                .toString();
    }
}
