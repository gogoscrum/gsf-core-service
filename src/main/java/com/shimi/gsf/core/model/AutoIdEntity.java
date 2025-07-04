package com.shimi.gsf.core.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * AutoIdEntity is a base class for entities that use an auto-generated ID.
 * It provides a simple way to manage the ID field for entities.
 * This class is marked as a MappedSuperclass, which means it can be used as a base class for other entity classes.
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AutoIdEntity implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
