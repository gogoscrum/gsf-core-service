package com.shimi.gsf.core.model;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

/**
 * TraceableEntity is a base class for entities that are traceable.
 * It provides a simple way to manage the created and updated timestamps for entities.
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class TraceableEntity implements Entity {
    protected Date createdTime;
    protected Date updatedTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public abstract User getCreatedBy();

    public abstract void setCreatedBy(User user);

    public abstract User getUpdatedBy();

    public abstract void setUpdatedBy(User user);

    public void setAllTraceInfo(User user) {
        Date now = new Date();

        this.createdTime = now;
        this.updatedTime = now;
        this.setCreatedBy(user);
        this.setUpdatedBy(user);
    }

    public void setUpdateTraceInfo(User user) {
        this.updatedTime = new Date();
        this.setUpdatedBy(user);
    }
}
