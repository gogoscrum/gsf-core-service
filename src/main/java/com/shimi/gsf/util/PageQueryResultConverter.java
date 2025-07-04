package com.shimi.gsf.util;

import com.shimi.gsf.core.model.Entity;
import com.shimi.gsf.core.model.EntityQueryResult;
import org.springframework.data.domain.Page;

/**
 * PageQueryResultConverter is a utility class that converts a Spring Data Page object
 * to an EntityQueryResult object.
 */
public class PageQueryResultConverter {
    private PageQueryResultConverter() {
    }

    /**
     * Converts a Spring Data Page object to an EntityQueryResult object.
     * @param page the Spring Data Page object to convert
     * @return the converted EntityQueryResult object
     * @param <T> the type of the entity
     */
    public static <T extends Entity> EntityQueryResult<T> toQueryResult(Page<T> page) {
        EntityQueryResult<T> result = new EntityQueryResult<>();

        result.setCurrentPage(page.getNumber() + 1L);
        result.setTotalElements(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        result.setResults(page.getContent());

        return result;
    }
}
