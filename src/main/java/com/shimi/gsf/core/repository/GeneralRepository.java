package com.shimi.gsf.core.repository;

import com.shimi.gsf.core.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * GeneralRepository is a generic interface that extends JpaRepository and JpaSpecificationExecutor.
 * It is used for CRUD operations and querying the database.
 * @param <T> the type of the entity
 */
@NoRepositoryBean
public interface GeneralRepository<T extends Entity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
