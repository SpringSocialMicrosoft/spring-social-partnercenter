package org.springframework.social.partnercenter.api.customer.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserState {
	ACTIVE("active"),
	INACTIVE("inactive");

	private String jsonValue;

	UserState(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
