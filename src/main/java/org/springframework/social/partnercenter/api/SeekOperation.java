package org.springframework.social.partnercenter.api;

public enum SeekOperation  {
	NEXT("next"), PREVIOUS("previous"), FIRST("first"), LAST("last"), PAGE_INDEX("pageIndex");

	private final String value;

	SeekOperation(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
