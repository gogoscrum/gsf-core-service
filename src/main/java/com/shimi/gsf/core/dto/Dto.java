package com.shimi.gsf.core.dto;

import com.shimi.gsf.core.model.Entity;

import java.io.Serializable;

/**
 * Dto is an interface that represents a Data Transfer Object (DTO).
 * It is used to transfer data between different layers (Controllers and Services) of the application.
 */
public interface Dto extends Serializable {
    Entity toEntity();
    Long getId();

    /**
     * Normalizes the DTO. Hides the fields that are not needed in the response for security reasons.
     * This method can be overridden in subclasses to provide specific normalization logic.
     * @return the normalized DTO
     */
    default Dto normalize() {
        return this;
    }
}
