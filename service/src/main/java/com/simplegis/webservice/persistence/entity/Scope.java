package com.simplegis.webservice.persistence.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Scope entity.
 */
public class Scope implements Serializable {

    private static final long serialVersionUID = -1771726702;

    private Integer id;
    private String name;
    private Integer version;
    private String keyWords;

    public Scope() {
    }

    public Scope(Scope value) {
        this.id = value.id;
        this.name = value.name;
        this.version = value.version;
        this.keyWords = value.keyWords;
    }

    public Scope(
            Integer id,
            String name,
            Integer version,
            String keyWords
    ) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.keyWords = keyWords;
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Scope scope = (Scope) o;

        return new EqualsBuilder()
                .append(id, scope.id)
                .append(name, scope.name)
                .append(version, scope.version)
                .append(keyWords, scope.keyWords)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(version)
                .append(keyWords)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("version", version)
                .append("keyWords", keyWords)
                .toString();
    }
}
