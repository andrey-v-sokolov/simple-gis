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

    private String number;
    private Long organizationId;

    public Phone() {
    }

    public Phone(Phone value) {
        this.number = value.number;
        this.organizationId = value.organizationId;
    }

    public Phone(
            String number,
            Long organizationId
    ) {
        this.number = number;
        this.organizationId = organizationId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
                .append(number, phone.number)
                .append(organizationId, phone.organizationId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number)
                .append(organizationId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("organizationId", organizationId)
                .toString();
    }
}
