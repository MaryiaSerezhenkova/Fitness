package by.academy.fitness.domain.dto;

import java.util.List;


import by.academy.fitness.dao.Filtering;
import by.academy.fitness.dao.Sorting;

public class PaginationContextDTO {

	private Integer skip;
	private Integer amount;
	private List<Filtering> filters;
	private List<Sorting> sortings;

	public PaginationContextDTO() {
		super();
	}

	public PaginationContextDTO(Integer skip, Integer amount, List<Filtering> filters, List<Sorting> sortings) {
		super();
		this.skip = skip;
		this.amount = amount;
		this.filters = filters;
		this.sortings = sortings;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<Filtering> getFilters() {
		return filters;
	}

	public void setFilters(List<Filtering> filters) {
		this.filters = filters;
	}

	public List<Sorting> getSortings() {
		return sortings;
	}

	public void setSortings(List<Sorting> sortings) {
		this.sortings = sortings;
	}

}
