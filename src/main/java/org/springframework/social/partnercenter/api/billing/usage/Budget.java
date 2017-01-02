package org.springframework.social.partnercenter.api.billing.usage;

import java.util.Map;

public class Budget {
	private double ammount;
	private Map<String, String> attributes;

	public double getAmmount() {
		return ammount;
	}

	public Budget setAmmount(double ammount) {
		this.ammount = ammount;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Budget setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
