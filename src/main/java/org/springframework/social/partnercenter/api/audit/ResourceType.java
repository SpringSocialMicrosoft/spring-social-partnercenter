package org.springframework.social.partnercenter.api.audit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceType {
	CUSTOMER("customer"),
	CUSTOMER_USER("customer_user"),
	ORDER("order"),
	SUBSCRIPTION("subscription"),
	LICENSE("license"),
	THIRD_PARTY_ADD_ON("third_party_add_on");

	private String value;

	ResourceType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public ResourceType value(String value) {
		this.value = value;
		return this;
	}
}
