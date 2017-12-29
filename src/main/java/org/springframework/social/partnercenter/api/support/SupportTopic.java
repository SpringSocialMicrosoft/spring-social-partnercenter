package org.springframework.social.partnercenter.api.support;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class SupportTopic {
	private String name;
	private String description;
	private int id;
	private ResourceAttributes attribute;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResourceAttributes getAttribute() {
		return attribute;
	}

	public void setAttribute(ResourceAttributes attribute) {
		this.attribute = attribute;
	}
}
