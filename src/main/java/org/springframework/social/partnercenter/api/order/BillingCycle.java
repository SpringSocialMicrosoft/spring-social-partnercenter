package org.springframework.social.partnercenter.api.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillingCycle {
	MONTHLY("monthly"),
	ANNUAL("annual"),
	UNKNOWN("unknown"),
	NONE("none");

	private String jsonValue;

	BillingCycle(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
