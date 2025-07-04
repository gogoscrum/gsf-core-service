package com.shimi.gsf.core.model;

import com.shimi.gsf.util.SnowflakeId;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * SnowflakeIdEntity is a base class for entities that use a snowflake ID.
 * It provides a simple way to manage the ID field for entities.
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class SnowflakeIdEntity implements Entity {
    @Id
    @SnowflakeId
    protected Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
