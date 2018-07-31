package org.springframework.social.partnercenter.api.customer.impl;

import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminCustomerTemplate extends CustomerTemplate implements AdminCustomerOperations {

	public AdminCustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
	}
}
