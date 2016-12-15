package org.springframework.social.partnercenter.api.customer;

import java.util.Map;

public class Role {
	private String id;
	private String name;
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public Role setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Role setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
