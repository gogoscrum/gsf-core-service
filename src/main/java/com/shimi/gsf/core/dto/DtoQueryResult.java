package com.shimi.gsf.core.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DtoQueryResult is a generic class that represents the result of a query for DTOs.
 * It contains the total number of elements, total pages, current page, and a list of results.
 * @param <T> the type of the DTO
 */
public class DtoQueryResult<T extends Dto> {
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
}
