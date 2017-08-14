package org.springframework.social.partnercenter.api.order.subscription.impl;

import static org.springframework.social.partnercenter.api.order.subscription.SubscriptionStatus.ACTIVE;
import static org.springframework.social.partnercenter.api.order.subscription.SubscriptionStatus.SUSPENDED;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;
import org.springframework.social.partnercenter.http.client.RestResource;

public class SubscriptionTemplate extends PagingResourceTemplate<Subscription> implements SubscriptionOperations {

	protected static final String SUBSCRIPTIONS = "subscriptions";
	private final RestResource restResource;

	public SubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>() {});
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
	public ResponseEntity<PartnerCenterResponse<Subscription>> getCustomersSubscriptions(String customerId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getSubscriptionsByOrderId(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.queryParam("order_id", orderId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getAddOnsForBySubscriptionId(String customerId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "addons")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
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
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>() {
				});
	}

	@Override
	public ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty) {
		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setQuantity(qty);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Upgrade>> getAvailableUpgrades(String customerId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "upgrades")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Upgrade>>() {});
	}

	@Override
	public ResponseEntity<UpgradeResult> upgradeSubscription(String customerId, String sourceSubscriptionId, Upgrade upgrade) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, sourceSubscriptionId, "upgrades")
				.post(upgrade, UpgradeResult.class);
	}
}
