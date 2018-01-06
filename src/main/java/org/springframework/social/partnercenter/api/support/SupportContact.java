package org.springframework.social.partnercenter.api.support;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.ResourceLinks;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class SupportContact {
	private String supportTenantId;
	private String supportMpnId;
	private String name;
	private ResourceLinks links;
	private ResourceAttributes attributes;

	public String getSupportTenantId() {
		return supportTenantId;
	}

	public void setSupportTenantId(String supportTenantId) {
		this.supportTenantId = supportTenantId;
	}

	public String getSupportMpnId() {
		return supportMpnId;
	}

	public void setSupportMpnId(String supportMpnId) {
		this.supportMpnId = supportMpnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceLinks getLinks() {
		return links;
	}

	public void setLinks(ResourceLinks links) {
		this.links = links;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}
}
