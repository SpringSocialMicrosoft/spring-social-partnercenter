package org.springframework.social.partnercenter.api.customer.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserDomainType {
	FEDERATED("federated"),
	MANAGED("managed"),
	NONE("none");

	private String jsonValue;

	UserDomainType(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
