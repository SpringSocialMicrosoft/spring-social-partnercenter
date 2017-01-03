package org.springframework.social.partnercenter.api.order.subscription.impl;

import static org.springframework.social.partnercenter.api.order.subscription.Subscription.Status.ACTIVE;
import static org.springframework.social.partnercenter.api.order.subscription.Subscription.Status.SUSPENDED;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.order.request.UpgradeSubscriptionRequest;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;

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
	public Subscription suspendSubscription(String customerId, String subscriptionId) {
		Subscription subscription = getById(customerId, subscriptionId);
		subscription.setStatus(SUSPENDED);
		return updateSubscription(customerId, subscriptionId, subscription);
	}

	@Override
	public Subscription reactivateSubscription(String customerId, String subscriptionId) {
		Subscription subscription = getById(customerId, subscriptionId);
		subscription.setStatus(ACTIVE);
		return updateSubscription(customerId, subscriptionId, subscription);
	}

	@Override
	public Subscription updateSubscriptionQuantity(String customerId, String subscriptionId, int qty) {
		Subscription subscription = getById(customerId, subscriptionId);
		subscription.setQuantity(String.valueOf(qty));
		return updateSubscription(customerId, subscriptionId, subscription);
	}
	//TODO: Need to look into this one
	@Override
	public String getSubscriptionUpgrade(String customerId, String subscriptionId) {
		return null;
	}
	//TODO: Need to look into this one
	@Override
	public GetSubscriptionListResponse transitionSubscription(String customerId, String sourceSubscriptionId, Subscription targetSubscription) {
		Subscription subscription = restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, sourceSubscriptionId, "upgrades")
				.get(Subscription.class);

		UpgradeSubscriptionRequest request = UpgradeSubscriptionRequest.builder()
				.setEligible(true)
				.setTargetOffer(subscription)
				.setQuantity(Integer.parseInt(subscription.getQuantity()))
				.build();

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, targetSubscription.getId(), "upgrades")
				.post(request, GetSubscriptionListResponse.class);
	}
}
