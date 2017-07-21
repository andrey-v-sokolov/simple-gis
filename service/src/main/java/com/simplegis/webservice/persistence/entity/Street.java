package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Street entity.
 */
public class Street implements Serializable {

    private static final long serialVersionUID = -38262671;

    private Long id;
    private String name;
    private BigDecimal length;
    private Long cityId;

    public Street() {
    }

    public Street(Street value) {
        this.id = value.id;
        this.name = value.name;
        this.length = value.length;
        this.cityId = value.cityId;
    }

    public Street(
            Long id,
            String name,
            BigDecimal length,
            Long cityId
    ) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.cityId = cityId;
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

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Street street = (Street) o;

        return new EqualsBuilder()
                .append(id, street.id)
                .append(name, street.name)
                .append(length, street.length)
                .append(cityId, street.cityId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(length)
                .append(cityId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("length", length)
                .append("cityId", cityId)
                .toString();
    }
}
