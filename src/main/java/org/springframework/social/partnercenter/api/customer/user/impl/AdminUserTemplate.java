package org.springframework.social.partnercenter.api.customer.user.impl;

import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminUserTemplate extends UserTemplate implements AdminUserOperations {

	public AdminUserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
	}
}
