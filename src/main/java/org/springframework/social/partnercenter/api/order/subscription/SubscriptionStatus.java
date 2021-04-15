package org.springframework.social.partnercenter.api.order.subscription;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionStatus {
	ACTIVE("active"),
	DELETED("deleted"),
	NONE("none"),
	SUSPENDED("suspended"),
	PENDING("pending");

	private String jsonValue;

	SubscriptionStatus(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
