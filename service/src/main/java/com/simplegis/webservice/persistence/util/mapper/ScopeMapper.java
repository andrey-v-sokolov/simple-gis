package com.simplegis.webservice.persistence.util.mapper;

import com.simplegis.common.dto.ScopeDto;
import com.simplegis.webservice.persistence.entity.Scope;

/**
 * Maps scope entity to dto.
 */
public final class ScopeMapper {

    /**
     * Maps scope entity to dto.
     * @param scope entity to map
     * @return dto.
     */
    public static ScopeDto toDto(Scope scope) {
        return new ScopeDto(
                scope.getId(),
                scope.getName(),
                scope.getVersion()
        );
    }
}
