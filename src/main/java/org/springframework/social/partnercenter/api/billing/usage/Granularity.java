package org.springframework.social.partnercenter.api.billing.usage;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Granularity {
	DAILY("daily"), HOURLY("hourly");

	private String value;

	@JsonValue
	public String jsonValue() {
		return value;
	}

	Granularity(String value){
		this.value = value;
	}


}
