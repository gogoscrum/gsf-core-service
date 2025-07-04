package com.shimi.gsf.core.event;

import com.shimi.gsf.core.model.Entity;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * EntityChangeEvent is an event that is published when an entity is created, updated, or deleted.
 * It contains the previous entity, the updated entity, and the action type (CREATE, UPDATE, DELETE).
 * This event is used to notify listeners about changes to entities in the system.
 * The source of the event is the current user that triggered the event.
 */
public class EntityChangeEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 2331230399767339234L;
    private Entity previousEntity;
    private Entity updatedEntity;
    private ActionType actionType;

    public EntityChangeEvent(Object source) {
        super(source);
    }

    public EntityChangeEvent(Object source, Entity previousEntity, Entity updatedEntity, ActionType actionType) {
        super(source);
        this.previousEntity = previousEntity;
        this.updatedEntity = updatedEntity;
        this.actionType = actionType;
    }

    public Entity getPreviousEntity() {
        return previousEntity;
    }

    public void setPreviousEntity(Entity previousEntity) {
        this.previousEntity = previousEntity;
    }

    public Entity getUpdatedEntity() {
        return updatedEntity;
    }

    public void setUpdatedEntity(Entity updatedEntity) {
        this.updatedEntity = updatedEntity;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public enum ActionType {
        CREATE, UPDATE, DELETE
    }
}