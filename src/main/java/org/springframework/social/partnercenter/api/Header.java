package org.springframework.social.partnercenter.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {
	String key;
	String value;

	public String getKey() {
		return key;
	}

	public Header setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Header setValue(String value) {
		this.value = value;
		return this;
	}
}
