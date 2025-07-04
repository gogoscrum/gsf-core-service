package com.shimi.gsf.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * BaseDto is an abstract class that implements the Dto interface.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
public abstract class BaseDto implements Dto, Serializable {
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
