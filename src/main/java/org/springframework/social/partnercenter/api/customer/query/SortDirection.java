package org.springframework.social.partnercenter.api.customer.query;

public enum SortDirection {
	ASC("ascending"), DESC("descending");

	private final String value;

	SortDirection(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
