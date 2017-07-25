package com.simplegis.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Street data transfer object.
 */
public class StreetDto implements Serializable {

    private static final long serialVersionUID = -38262672;

    private Long id;
    private String name;
    private BigDecimal length;
    private Long cityId;
    private Integer version;

    public StreetDto() {
    }

    public StreetDto(StreetDto value) {
        this.id = value.id;
        this.name = value.name;
        this.length = value.length;
        this.cityId = value.cityId;
        this.version = value.version;
    }

    public StreetDto(
            Long id,
            String name,
            BigDecimal length,
            Long cityId,
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

        StreetDto streetDto = (StreetDto) o;

        return new EqualsBuilder()
                .append(id, streetDto.id)
                .append(name, streetDto.name)
                .append(length, streetDto.length)
                .append(cityId, streetDto.cityId)
                .append(version, streetDto.version)
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
