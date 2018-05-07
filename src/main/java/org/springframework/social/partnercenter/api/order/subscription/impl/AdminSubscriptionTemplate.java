package org.springframework.social.partnercenter.api.order.subscription.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.Conversion;
import org.springframework.social.partnercenter.api.order.subscription.ConversionResult;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminSubscriptionTemplate extends SubscriptionTemplate implements AdminSubscriptionOperations {
	public static final String CONVERSIONS = "conversions";
	private RestResource restResource;

	public AdminSubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Conversion>> conversions(String customerId, String subscriptionId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(subscriptionId, "[Assertion failed] - subscriptionId argument must be null");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Conversion>>() {});
	}

	@Override
	public ResponseEntity<ConversionResult> convertTrial(String customerId, String subscriptionId, Conversion conversion) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(subscriptionId, "[Assertion failed] - subscriptionId argument must be null");
		notNull(conversion, "[Assertion failed] - conversion argument must be null");

		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.post(conversion, ConversionResult.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Upgrade>> getAvailableUpgrades(String customerId, String subscriptionId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(subscriptionId, "[Assertion failed] - subscriptionId argument must be null");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "upgrades")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Upgrade>>() {});
	}

	@Override
	public ResponseEntity<UpgradeResult> upgradeSubscription(String customerId, String sourceSubscriptionId, Upgrade upgrade) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(sourceSubscriptionId, "[Assertion failed] - sourceSubscriptionId argument must be null");
		notNull(upgrade, "[Assertion failed] - upgrade argument must be null");
		
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, sourceSubscriptionId, "upgrades")
				.post(upgrade, UpgradeResult.class);
	}
}
