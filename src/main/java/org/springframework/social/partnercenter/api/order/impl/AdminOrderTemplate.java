package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionProvisioningState;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminOrderTemplate extends OrderTemplate implements AdminOrderOperations {
	private final RestResource restResource;

	public AdminOrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<SubscriptionProvisioningState> getSubscriptionProvisioningState(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId, "provisioningstatus")
				.get(SubscriptionProvisioningState.class);
	}
}
