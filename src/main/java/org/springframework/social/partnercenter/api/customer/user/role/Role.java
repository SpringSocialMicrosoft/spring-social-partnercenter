package org.springframework.social.partnercenter.api.customer.user.role;

import org.springframework.social.partnercenter.api.ResourceBase;

public class Role extends ResourceBase{
	private String id;
	private String name;
	private String category;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
