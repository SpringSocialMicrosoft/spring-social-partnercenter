package org.springframework.social.partnercenter.api.support.servicerequest;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  ServiceRequestSeverity {
	UNKNOWN("unknown"), CRITICAL("critical"), MODERATE("moderate"), MINIMAL("minimal");

	private String jsonValue;

	ServiceRequestSeverity(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	@JsonValue
	public String getJsonValue() {
		return jsonValue;
	}
}
