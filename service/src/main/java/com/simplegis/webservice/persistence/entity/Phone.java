package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Phone entity.
 */
public class Phone implements Serializable {

    private static final long serialVersionUID = -1975356727;

    private Long id;
    private String number;
    private Long organizationId;
    private Integer version;

    public Phone() {
    }

    public Phone(Phone value) {
        this.id = value.id;
        this.number = value.number;
        this.organizationId = value.organizationId;
        this.version = value.version;
    }

    public Phone(
            Long id,
            String number,
            Long organizationId,
            Integer version
    ) {
        this.id = id;
        this.number = number;
        this.organizationId = organizationId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

        Phone phone = (Phone) o;

        return new EqualsBuilder()
                .append(id, phone.id)
                .append(number, phone.number)
                .append(organizationId, phone.organizationId)
                .append(version, phone.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(number)
                .append(organizationId)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("number", number)
                .append("organizationId", organizationId)
                .append("version", version)
                .toString();
    }
}
