package org.springframework.social.partnercenter.api.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ContractType {
	SUBSCRIPTION("subscription"),
	PRODUCT_KEY("productKey"),
	REDEMPTION_CODE("redemptionCode");

	private String jsonValue;

	ContractType(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
