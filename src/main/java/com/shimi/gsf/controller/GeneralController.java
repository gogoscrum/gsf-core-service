package com.shimi.gsf.controller;

import com.shimi.gsf.core.dto.Dto;
import com.shimi.gsf.core.dto.DtoQueryResult;
import com.shimi.gsf.core.model.Entity;
import com.shimi.gsf.core.model.Filter;
import com.shimi.gsf.core.service.GeneralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;

/**
 * GeneralController is a generic controller that provides CRUD operations for entities.
 * Please note if your controller extends this class, all CRUD operations will be exposed.
 *
 * @param <T> the type of the DTO
 * @param <F> the type of the filter
 */
public abstract class GeneralController<T extends Dto, E extends Entity, F extends Filter> {
    /**
     * Get the service for the entity.
     *
     * @return the service
     */
    protected abstract GeneralService<E, F> getService();

    @Operation(summary = "Create a new entity")
    @PostMapping
    public Dto create(@RequestBody T dto) {
        return getService().create((E) dto.toEntity()).toDto();
    }

    @Operation(summary = "Get an entity")
    @Parameters({@Parameter(name = "id", description = "The entity ID")})
    @GetMapping("/{id}")
    public Dto get(@PathVariable Long id) {
        return getService().get(id).toDto();
    }

    @Operation(summary = "Search entities")
    @GetMapping
    public DtoQueryResult<Dto> search(F filter) {
        return getService().search(filter).toDto();
    }

    @Operation(summary = "Update an entity")
    @Parameters({@Parameter(name = "id", description = "The entity ID")})
    @PutMapping("/{id}")
    public Dto update(@PathVariable Long id, @RequestBody T dto) {
        return getService().update(id, (E) dto.toEntity()).toDto();
    }

    @Operation(summary = "Delete an entity")
    @Parameters({@Parameter(name = "id", description = "The entity ID")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }
}
