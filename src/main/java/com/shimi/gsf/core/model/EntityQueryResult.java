package com.shimi.gsf.core.model;

import com.shimi.gsf.core.dto.Dto;
import com.shimi.gsf.core.dto.DtoQueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EntityQueryResult is a class that represents the result of a query for entities.
 * It contains the total number of elements, total number of pages, current page, and a list of results.
 * It also provides methods to convert the results to DTOs (Data Transfer Objects).
 * @param <T> the type of entity
 */
public class EntityQueryResult<T extends Entity> {
    private long totalElements;
    private long totalPages;
    private long currentPage;
    private List<T> results = new ArrayList<>();

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public DtoQueryResult<Dto> toDto() {
        return this.toDto(false);
    }

    public DtoQueryResult<Dto> toDto(boolean detailed) {
        return this.toDto(detailed, false);
    }

    public DtoQueryResult<Dto> toDto(boolean detailed, boolean normalized) {
        DtoQueryResult<Dto> dtos = new DtoQueryResult<>();

        dtos.setCurrentPage(this.currentPage);
        dtos.setTotalElements(this.totalElements);
        dtos.setTotalPages(this.totalPages);
        dtos.setResults(this.results.stream().map(item -> {
            Dto dto = item.toDto(detailed);
            if (normalized) {
                dto = dto.normalize();
            }
            return dto;
        }).collect(Collectors.toList()));

        return dtos;
    }

    public DtoQueryResult<Dto> toDto(String[] ignoreProps, boolean normalized) {
        DtoQueryResult<Dto> dtos = new DtoQueryResult<>();

        dtos.setCurrentPage(this.currentPage);
        dtos.setTotalElements(this.totalElements);
        dtos.setTotalPages(this.totalPages);
        dtos.setResults(this.results.stream().map(item -> {
            Dto dto = item.toDto(ignoreProps);
            if (normalized) {
                dto = dto.normalize();
            }
            return dto;
        }).collect(Collectors.toList()));

        return dtos;
    }
}
