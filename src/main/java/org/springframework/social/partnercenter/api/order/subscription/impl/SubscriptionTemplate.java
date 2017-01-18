package org.springframework.social.partnercenter.api.order.subscription.impl;

import static org.springframework.social.partnercenter.api.order.subscription.Subscription.Status.ACTIVE;
import static org.springframework.social.partnercenter.api.order.subscription.Subscription.Status.SUSPENDED;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListListResponse;
import org.springframework.social.partnercenter.api.order.request.UpgradeSubscriptionRequest;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class SubscriptionTemplate extends AbstractTemplate implements SubscriptionOperations {

	private static final String SUBSCRIPTIONS = "subscriptions";
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
	public ResponseEntity<Subscription> getById(String customerTenantId, String id) {
		return restResource.request()
				.pathSegment(customerTenantId, SUBSCRIPTIONS, id)
				.get(Subscription.class);
	}

	@Override
	public ResponseEntity<GetSubscriptionListListResponse> getCustomersSubscriptions(String customerId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.get(GetSubscriptionListListResponse.class);
	}

	@Override
	public ResponseEntity<GetSubscriptionListListResponse> getSubscriptionsByOrderId(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.queryParam("order_id", orderId)
				.get(GetSubscriptionListListResponse.class);
	}

	@Override
	public ResponseEntity<GetSubscriptionListListResponse> getAddOnsForBySubscriptionId(String customerId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "addons")
				.get(GetSubscriptionListListResponse.class);
	}

	@Override
	public ResponseEntity<Subscription> updateSubscription(String customerId, String subscriptionId, Subscription subscription) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId)
				.patch(subscription, Subscription.class);
	}

	@Override
	public ResponseEntity<Subscription> suspendSubscription(String customerId, String subscriptionId) {
		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setStatus(SUSPENDED);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<Subscription> reactivateSubscription(String customerId, String subscriptionId) {
		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setStatus(ACTIVE);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getAllSubscriptionsForPartner(String customerId, String mpnId, int offset, int size) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.queryParam("mpn_id", mpnId)
				.queryParam("offset", offset)
				.queryParam("size", size)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>() {});
	}

	@Override
	public ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty) {
		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setQuantity(qty);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}
	//TODO: Need to look into this one
//	@Override
//	public ResponseEntity<String> getSubscriptionUpgrade(String customerId, String subscriptionId) {
//		return null;
//	}
	//TODO: Need to look into this one
	@Override
	public ResponseEntity<GetSubscriptionListListResponse> transitionSubscription(String customerId, String sourceSubscriptionId, Subscription targetSubscription) {
		ResponseEntity<Subscription> subscription = restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, sourceSubscriptionId, "upgrades")
				.get(Subscription.class);

		UpgradeSubscriptionRequest request = UpgradeSubscriptionRequest.builder()
				.setEligible(true)
				.setTargetOffer(subscription.getBody())
				.setQuantity(subscription.getBody().getQuantity())
				.build();

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, targetSubscription.getId(), "upgrades")
				.post(request, GetSubscriptionListListResponse.class);
	}
}
