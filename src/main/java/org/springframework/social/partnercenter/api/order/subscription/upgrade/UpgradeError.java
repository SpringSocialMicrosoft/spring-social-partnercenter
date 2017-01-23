package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import java.util.Map;

public class UpgradeError {
	private int code;
	private String description;
	private Map<String, String> attributes;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
