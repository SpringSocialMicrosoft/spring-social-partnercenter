package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminOrderTemplate extends OrderTemplate implements AdminOrderOperations {

	public AdminOrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
	}
}
