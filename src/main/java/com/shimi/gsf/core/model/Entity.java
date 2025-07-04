package com.shimi.gsf.core.model;

import com.shimi.gsf.core.dto.Dto;

import java.io.Serializable;

/**
 * Entity is a base interface for all entities in the application.
 * It provides a method to get the ID of the entity and a method to convert the entity to a DTO (Data Transfer Object).
 */
public interface Entity extends Serializable {
    Long getId();
    void  setId(Long id);
    Dto toDto();

    default Dto toDto(boolean detailed) {
        return toDto();
    }

    default Dto toDto(String... ignoreProps) {
        return toDto();
    }
}
