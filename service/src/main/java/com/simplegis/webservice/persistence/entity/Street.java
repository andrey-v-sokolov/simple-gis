package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Street entity.
 */
public class Street implements Serializable {

    private static final long serialVersionUID = -38262671;

    private BigInteger id;
    private String name;
    private BigDecimal length;
    private BigInteger cityId;
    private Integer version;

    public Street() {
    }

    public Street(Street value) {
        this.id = value.id;
        this.name = value.name;
        this.length = value.length;
        this.cityId = value.cityId;
        this.version = value.version;
    }

    public Street(
            BigInteger id,
            String name,
            BigDecimal length,
            BigInteger cityId,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.cityId = cityId;
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

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigInteger getCityId() {
        return cityId;
    }

    public void setCityId(BigInteger cityId) {
        this.cityId = cityId;
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

        Street street = (Street) o;

        return new EqualsBuilder()
                .append(id, street.id)
                .append(name, street.name)
                .append(length, street.length)
                .append(cityId, street.cityId)
                .append(version, street.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(length)
                .append(cityId)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("length", length)
                .append("cityId", cityId)
                .append("version", version)
                .toString();
    }
}
