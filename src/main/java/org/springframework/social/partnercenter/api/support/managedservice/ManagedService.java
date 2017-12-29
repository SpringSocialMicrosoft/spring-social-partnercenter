package org.springframework.social.partnercenter.api.support.managedservice;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ManagedService {
	private String id;
	private String name;
	private String groupName;
	private ManagedServiceLinks links;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ManagedServiceLinks getLinks() {
		return links;
	}

	public void setLinks(ManagedServiceLinks links) {
		this.links = links;
	}
}
