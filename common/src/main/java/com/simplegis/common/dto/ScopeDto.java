package com.simplegis.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Scope data transfer object.
 */
public class ScopeDto implements Serializable {

    private static final long serialVersionUID = -1771726702;

    private Integer id;
    private String name;
    private Integer version;

    public ScopeDto() {
    }

    public ScopeDto(ScopeDto value) {
        this.id = value.id;
        this.name = value.name;
        this.version = value.version;
    }

    public ScopeDto(
            Integer id,
            String name,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        ScopeDto scopeDto = (ScopeDto) o;

        return new EqualsBuilder()
                .append(id, scopeDto.id)
                .append(name, scopeDto.name)
                .append(version, scopeDto.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("version", version)
                .toString();
    }
}
