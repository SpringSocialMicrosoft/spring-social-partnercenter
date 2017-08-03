package org.springframework.social.partnercenter.api.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillingType {
	USAGE("usage"),
	LICENSE("license"),
	NONE("none");

	private String jsonValue;

	BillingType(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
