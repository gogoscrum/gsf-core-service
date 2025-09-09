package com.shimi.gsf.core.service;

import com.shimi.gsf.core.event.EntityChangeEvent;
import com.shimi.gsf.core.exception.EntityNotFoundException;
import com.shimi.gsf.core.model.*;
import com.shimi.gsf.core.repository.GeneralRepository;
import com.shimi.gsf.util.PageQueryResultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * GeneralServiceImpl is a generic implementation of the GeneralService interface.
 * It provides CRUD operations and search functionality.
 * @param <T> the type of the entity
 * @param <K> the type of the filter
 */
@Transactional
public abstract class GeneralServiceImpl<T extends Entity, K extends Filter> extends BaseService implements GeneralService<T, K> {
    public static final Logger log = LoggerFactory.getLogger(GeneralServiceImpl.class);
    public static final String[] DEFAULT_UPDATE_IGNORED_PROPS = new String[]{"id", "createdTime", "createdBy"};

    /**
     * Get the repository for the entity. All subclasses must implement this method. The returned repository
     * will be used for all CRUD operations.
     * @return the repository for the entity
     */
    protected abstract GeneralRepository<T> getRepository();

    public T get(Long id) {
        Optional<T> optionalT = getRepository().findById(id);
        if (optionalT.isPresent()) {
            return optionalT.get();
        } else {
            throw new EntityNotFoundException(MessageFormat.format("{0} not found by id {1} ", getEntityType(), id));
        }
    }

    /**
     * Get the entity type name. This method is used for logging and error messages.
     * @return the entity type name
     */
    private String getEntityType() {
        Class<T> actualTypeArgument = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        return actualTypeArgument.getSimpleName();
    }

    public T create(T entity) {
        this.beforeCreate(entity);

        entity.setId(null);
        if (entity instanceof TraceableEntity) {
            ((TraceableEntity) entity).setAllTraceInfo(getCurrentUser());
        }

        T savedEntity = getRepository().save(entity);
        log.info("Created {}", savedEntity);

        eventPublisher.publishEvent(new EntityChangeEvent(this, null, savedEntity, EntityChangeEvent.ActionType.CREATE));

        this.afterCreate(savedEntity);

        return savedEntity;
    }

    /**
     * This method is called before the creation. It can be overridden by subclasses to perform
     * additional actions before the entity is created.
     * @param entity the entity to create
     */
    protected void beforeCreate(T entity) {
    }

    /**
     * This method is called after the creation. It can be overridden by subclasses to perform
     * additional actions after the entity is created.
     * @param entity the created entity
     */
    protected void afterCreate(T entity) {
    }

    public T update(Long id, T entity) {
        T existingEntity = get(id);

        T clonedExistingEntity = cloneEntity(existingEntity);

        this.beforeUpdate(id, existingEntity, entity);

        BeanUtils.copyProperties(entity, existingEntity, getUpdateIgnoredProps());
        if (existingEntity instanceof TraceableEntity) {
            ((TraceableEntity) existingEntity).setUpdateTraceInfo(getCurrentUser());
        }

        T updatedEntity = getRepository().save(existingEntity);
        log.info("Updated {}", updatedEntity);

        eventPublisher.publishEvent(new EntityChangeEvent(this, clonedExistingEntity, updatedEntity, EntityChangeEvent.ActionType.UPDATE));

        this.afterUpdate(id, clonedExistingEntity, updatedEntity);

        return updatedEntity;
    }

    /**
     * Clone the entity. This method is used to create a copy of the entity before updating it.
     * This is useful for auditing purposes.
     * @param entity the entity to clone
     * @return the cloned entity
     */
    private T cloneEntity(T entity) {
        try {
            T cloned = (T) Class.forName(entity.getClass().getName()).getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, cloned);
            return cloned;
        } catch (Exception e) {
            log.error("Error occurred while clone entity: ", e);
            return entity;
        }
    }

    /**
     * This method is called before the update. It can be overridden by subclasses to perform
     * additional actions before the entity is updated.
     * @param id the ID of the entity to update
     * @param existingEntity the existing entity
     * @param newEntity the new entity with updated values
     */
    protected void beforeUpdate(Long id, T existingEntity, T newEntity) {
    }

    /**
     * This method is called after the update. It can be overridden by subclasses to perform
     * additional actions after the entity is updated.
     * @param id the ID of the entity to update
     * @param existingEntity the existing entity
     * @param updatedEntity the updated entity
     */
    protected void afterUpdate(Long id, T existingEntity, T updatedEntity) {
    }

    /**
     * Get the properties to ignore when updating the entity.
     * This method is used to specify which properties should not be updated.
     * @return the properties to ignore
     */
    protected String[] getUpdateIgnoredProps() {
        return DEFAULT_UPDATE_IGNORED_PROPS;
    }

    public List<T> saveAll(List<T> entities) {
        return getRepository().saveAll(entities);
    }

    public void delete(Long id) {
        T entity = this.get(id);

        this.beforeDelete(entity);

        getRepository().delete(entity);
        log.info("Deleted {}", entity);

        if (entity instanceof TraceableEntity) {
            ((TraceableEntity) entity).setUpdateTraceInfo(getCurrentUser());
        }

        eventPublisher.publishEvent(new EntityChangeEvent(this, entity, null, EntityChangeEvent.ActionType.DELETE));

        this.afterDelete(entity);
    }

    /**
     * This method is called before the deletion. It can be overridden by subclasses to perform
     * additional actions before the entity is deleted.
     * @param entity the entity to delete
     */
    protected void beforeDelete(T entity) {
    }

    /**
     * This method is called after the deletion. It can be overridden by subclasses to perform
     * additional actions after the entity is deleted.
     * @param entity the deleted entity
     */
    protected void afterDelete(T entity) {
    }

    public EntityQueryResult<T> search(@NonNull K filter) {
        Objects.requireNonNull(filter, "Search filter cannot be null");

        Specification<T> querySpec = this.toSpec(filter);

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), filter.toSort());

        Page<T> contents = this.getRepository().findAll(querySpec, pageable);

        return PageQueryResultConverter.toQueryResult(contents);
    }

    public List<T> searchAll(@NonNull K filter) {
        Objects.requireNonNull(filter, "Search filter cannot be null");
        return this.getRepository().findAll(this.toSpec(filter), filter.toSort());
    }

    public long count(@NonNull K filter) {
        Objects.requireNonNull(filter, "Search filter cannot be null");
        return this.getRepository().count(this.toSpec(filter));
    }

    /**
     * Convert the filter to a specification. This method is used to create a specification based on the filter provided.
     * This method must be implemented by subclasses to provide the specific filtering logic for the entity.
     * @param filter the filter to convert
     * @return the specification
     */
    protected abstract Specification<T> toSpec(K filter);
}