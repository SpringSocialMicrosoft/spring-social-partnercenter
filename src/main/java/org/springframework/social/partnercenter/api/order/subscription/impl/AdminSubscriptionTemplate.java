package org.springframework.social.partnercenter.api.order.subscription.impl;

import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminSubscriptionTemplate extends SubscriptionTemplate implements AdminSubscriptionOperations {

	public AdminSubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
	}
}
