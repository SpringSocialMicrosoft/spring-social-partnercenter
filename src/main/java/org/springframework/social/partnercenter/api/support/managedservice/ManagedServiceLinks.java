package org.springframework.social.partnercenter.api.support.managedservice;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ManagedServiceLinks {
	private Link adminService;
	private Link serviceHealth;
	private Link serviceTicket;
	private ResourceAttributes attributes;

	public Link getAdminService() {
		return adminService;
	}

	public void setAdminService(Link adminService) {
		this.adminService = adminService;
	}

	public Link getServiceHealth() {
		return serviceHealth;
	}

	public void setServiceHealth(Link serviceHealth) {
		this.serviceHealth = serviceHealth;
	}

	public Link getServiceTicket() {
		return serviceTicket;
	}

	public void setServiceTicket(Link serviceTicket) {
		this.serviceTicket = serviceTicket;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}
}
