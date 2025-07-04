package com.shimi.gsf.core.service;

import com.shimi.gsf.core.event.EntityChangeEvent;
import com.shimi.gsf.core.exception.EntityNotFoundException;
import com.shimi.gsf.core.model.Entity;
import com.shimi.gsf.core.model.EntityQueryResult;
import com.shimi.gsf.core.model.Filter;

import java.util.List;

/**
 * GeneralService is a generic interface that provides CRUD operations and search functionality.
 * @param <T> the type of the entity
 * @param <K> the type of the filter
 */
public interface GeneralService<T extends Entity, K extends Filter> {
    /**
     * Get entity by ID.
     * @param id the ID of the entity
     * @return the entity
     * @throws EntityNotFoundException if the entity is not found
     */
    T get(Long id);

    /**
     * Create a new entity. And {@link EntityChangeEvent} will be published after the entity is created.
     * @param entity the entity to create
     * @return the created entity
     */
    T create(T entity);

    /**
     * Update an existing entity. And {@link EntityChangeEvent} will be published after the entity is updated.
     * @param id the ID of the entity to update
     * @param entity the entity with updated values
     * @return the updated entity
     * @throws EntityNotFoundException if the entity is not found by ID
     */
    T update(Long id, T entity);

    /**
     * Save all entities. Please be aware that no event will be published after the entities are saved.
     * @param entities the list of entities to save
     * @return the list of saved entities
     */
    List<T> saveAll(List<T> entities);

    /**
     * Delete an entity by ID. And {@link EntityChangeEvent} will be published after the entity is deleted.
     * @param id the ID of the entity to delete
     * @throws EntityNotFoundException if the entity is not found
     */
    void delete(Long id);

    /**
     * Search for entities based on the provided filter. The filter is used to create a specification for the query.
     * @param filter the filter to use for the search
     * @return the result of the search, including the list of entities and pagination information
     */
    EntityQueryResult<T> search(K filter);

    /**
     * Search for all entities based on the provided filter. The filter is used to create a specification for the query.
     * Since this method does not support pagination, it is recommended to use this method only for small datasets.
     * @param filter the filter to use for the search
     * @return the list of entities that match the filter without pagination
     */
    List<T> searchAll(K filter);

    /**
     * Count the number of entities that match the provided filter.
     * @param filter the filter to use for the count
     * @return the number of entities that match the filter
     */
    long count(K filter);
}
