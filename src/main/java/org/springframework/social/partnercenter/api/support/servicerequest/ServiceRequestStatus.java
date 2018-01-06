package org.springframework.social.partnercenter.api.support.servicerequest;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  ServiceRequestStatus {
	NONE("none"), OPEN("open"), CLOSED("closed"), ATTENTION_NEEDED("attention_needed");

	private String jsonValue;

	ServiceRequestStatus(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	@JsonValue
	public String getJsonValue() {
		return jsonValue;
	}
}
