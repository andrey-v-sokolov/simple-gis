package com.simplegis.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * City data transfer object.
 */
public class CityDto implements Serializable {

    private static final long serialVersionUID = -1826957842;

    private BigInteger id;
    private String name;
    private BigDecimal area;
    private Integer population;
    private Integer version;

    public CityDto() {
    }

    public CityDto(CityDto value) {
        this.id = value.id;
        this.name = value.name;
        this.area = value.area;
        this.population = value.population;
        this.version = value.version;
    }

    public CityDto(
            BigInteger id,
            String name,
            BigDecimal area,
            Integer population,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
        this.version = version;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CityDto city = (CityDto) o;

        return new EqualsBuilder()
                .append(id, city.id)
                .append(name, city.name)
                .append(area, city.area)
                .append(population, city.population)
                .append(version, city.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(area)
                .append(population)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("area", area)
                .append("population", population)
                .append("version", version)
                .toString();
    }
}
