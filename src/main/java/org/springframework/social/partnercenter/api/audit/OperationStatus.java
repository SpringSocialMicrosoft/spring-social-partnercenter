package org.springframework.social.partnercenter.api.audit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationStatus {
	SUCCEEDED("succeeded"),
	FAILED("failed"),
	PROGRESS("progress");

	private String value;

	OperationStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public OperationStatus value(String value) {
		this.value = value;
		return this;
	}
}
