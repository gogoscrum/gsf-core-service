package com.shimi.gsf.core.dto;

import java.util.Date;

/**
 * TraceableDto is an abstract class that extends BaseDto.
 * It includes the user who created and updated the DTO, as well as the timestamps for creation and update.
 */
@SuppressWarnings("serial")
public abstract class TraceableDto extends BaseDto {
    protected UserDto createdBy;
    protected UserDto updatedBy;
    protected Date createdTime;
    protected Date updatedTime;

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public UserDto getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserDto updatedBy) {
        this.updatedBy = updatedBy;
    }

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
}
