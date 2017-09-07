package org.springframework.social.partnercenter.api.order.subscription.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminSubscriptionTemplate extends SubscriptionTemplate implements AdminSubscriptionOperations {
	private static final String SUBSCRIPTIONS = "subscriptions";
	private RestResource restResource;

	public AdminSubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
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
