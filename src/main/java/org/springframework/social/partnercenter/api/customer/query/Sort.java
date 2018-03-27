package org.springframework.social.partnercenter.api.customer.query;

public class Sort {
	private String sortField;
	private SortDirection sortDirection;

	private Sort(String sortField, SortDirection sortDirection) {
		this.sortField = sortField;
		this.sortDirection = sortDirection;
	}

	public static Sort of(String sortField, SortDirection sortDirection) {
		return new Sort(sortField, sortDirection);
	}

	public String getSortField() {
		return sortField;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}
}
