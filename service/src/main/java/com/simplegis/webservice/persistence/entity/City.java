package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * City entity.
 */
public class City implements Serializable {

    private static final long serialVersionUID = -1826957841;

    private Long id;
    private String name;
    private BigDecimal area;
    private Integer population;

    public City() {
    }

    public City(City value) {
        this.id = value.id;
        this.name = value.name;
        this.area = value.area;
        this.population = value.population;
    }

    public City(
            Long id,
            String name,
            BigDecimal area,
            Integer population
    ) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        City city = (City) o;

        return new EqualsBuilder()
                .append(id, city.id)
                .append(name, city.name)
                .append(area, city.area)
                .append(population, city.population)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(area)
                .append(population)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("area", area)
                .append("population", population)
                .toString();
    }
}
