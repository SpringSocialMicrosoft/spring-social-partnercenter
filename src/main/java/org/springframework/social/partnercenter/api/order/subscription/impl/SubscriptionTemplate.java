package org.springframework.social.partnercenter.api.order.subscription.impl;

import static org.springframework.social.partnercenter.api.order.subscription.SubscriptionStatus.ACTIVE;
import static org.springframework.social.partnercenter.api.order.subscription.SubscriptionStatus.SUSPENDED;
import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.Conversion;
import org.springframework.social.partnercenter.api.order.subscription.ConversionResult;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;
import org.springframework.social.partnercenter.http.client.RestResource;

public class SubscriptionTemplate extends PagingResourceTemplate<Subscription> implements SubscriptionOperations {

	public static final String CONVERSIONS = "conversions";
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
	public ResponseEntity<Subscription> getById(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId)
				.get(Subscription.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getCustomersSubscriptions(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getSubscriptionsByOrderId(String customerId, String orderId) {
		notNull(customerId, "customerId");
		notNull(orderId, "orderId");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS)
				.queryParam("order_id", orderId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getAddOnsForBySubscriptionId(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "addons")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>(){});
	}

	@Override
	public ResponseEntity<Subscription> updateSubscription(String customerId, String subscriptionId, Subscription subscription) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(subscription, "subscription");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId)
				.patch(subscription, Subscription.class);
	}

	@Override
	public ResponseEntity<Subscription> suspendSubscription(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");

		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setStatus(SUSPENDED);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<Subscription> reactivateSubscription(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");

		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setStatus(ACTIVE);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getAllSubscriptionsForPartner(String customerId, String mpnId, int offset, int size) {
		notNull(customerId, "customerId");
		notNull(mpnId, "mpnId");

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
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");

		ResponseEntity<Subscription> subscription = getById(customerId, subscriptionId);
		subscription.getBody().setQuantity(qty);
		return updateSubscription(customerId, subscriptionId, subscription.getBody());
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Conversion>> conversions(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Conversion>>() {});
	}

	@Override
	public ResponseEntity<ConversionResult> convertTrial(String customerId, String subscriptionId, Conversion conversion) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(conversion, "conversion");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.post(conversion, ConversionResult.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Upgrade>> getAvailableUpgrades(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "upgrades")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Upgrade>>() {});
	}

	@Override
	public ResponseEntity<UpgradeResult> upgradeSubscription(String customerId, String sourceSubscriptionId, Upgrade upgrade) {
		notNull(customerId, "customerId");
		notNull(sourceSubscriptionId, "sourceSubscriptionId");
		notNull(upgrade, "upgrade");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, sourceSubscriptionId, "upgrades")
				.post(upgrade, UpgradeResult.class);
	}
}
