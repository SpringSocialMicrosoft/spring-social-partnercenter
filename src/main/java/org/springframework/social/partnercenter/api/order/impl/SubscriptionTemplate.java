package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;
import org.springframework.social.partnercenter.api.order.Subscription;
import org.springframework.social.partnercenter.api.order.SubscriptionOperations;

public class SubscriptionTemplate extends AbstractTemplate implements SubscriptionOperations {

	public static final String SUBSCRIPTIONS = "subscriptions";
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
				.pathSegment(customerTenantId, SUBSCRIPTIONS, id)
				.get(Subscription.class);
	}

	@Override
	public GetSubscriptionListResponse getCustomersSubscriptions(String customerId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.get(GetSubscriptionListResponse.class);
	}

	@Override
	public GetSubscriptionListResponse getSubscriptionsByOrderId(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.queryParam("order_id", orderId)
				.get(GetSubscriptionListResponse.class);
	}

	@Override
	public GetSubscriptionListResponse getAddOnsForBySubscriptionId(String customerId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "addons")
				.get(GetSubscriptionListResponse.class);
	}

	@Override
	public Subscription updateSubscription(String customerId, String subscriptionId, Subscription subscription) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId)
				.patch(subscription, Subscription.class);
	}

	@Override
	public String getSubscriptionUpgrade(String customerId, String subscriptionId) {
		return null;
	}

	@Override
	public String upgradeSubscription(String customerId, String targetSubscription, Subscription target) {
		return null;
	}
}
