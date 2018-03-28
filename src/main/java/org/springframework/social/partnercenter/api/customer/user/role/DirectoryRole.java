package org.springframework.social.partnercenter.api.customer.user.role;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceBase;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class DirectoryRole extends ResourceBase{
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
