package org.springframework.social.partnercenter.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiFault {
	@JsonProperty("code")
	private String errorCode;
	@JsonProperty("description")
	private String errorMessage;
	@JsonProperty("data")
	private Map<String, String> attributes = new HashMap<>();

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		attributes.forEach((String s) -> {
			StringTokenizer tokenizer = new StringTokenizer(s, ":");
			if (tokenizer.countTokens() == 2){
				this.attributes.put(tokenizer.nextToken().trim(), tokenizer.nextToken().trim());
			}
		});
	}
}
