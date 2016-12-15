package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.order.Subscription;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionsResponse;
import org.springframework.social.partnercenter.api.order.SubscriptionOperations;

public class SubscriptionTemplate extends AbstractTemplate implements SubscriptionOperations {

	private final RestResource restResource;

	public SubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}

	@Override
	public Subscription getById(String customerTenantId, String id) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", id)
				.get(Subscription.class);
	}

	@Override
	public GetSubscriptionsResponse getCustomersSubscriptions(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions")
				.get(GetSubscriptionsResponse.class);
	}
}
