package org.springframework.social.partnercenter.api.order.subscription.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.Conversion;
import org.springframework.social.partnercenter.api.order.subscription.ConversionResult;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminSubscriptionTemplate extends SubscriptionTemplate implements AdminSubscriptionOperations {
	public static final String CONVERSIONS = "conversions";
	private RestResource restResource;

	public AdminSubscriptionTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Conversion>> conversions(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Conversion>>() {});
	}

	@Override
	public ResponseEntity<ConversionResult> convertTrial(String customerTenantyId, String subscriptionId, Conversion conversion) {
		return restResource.request()
				.pathSegment(customerTenantyId, SUBSCRIPTIONS, subscriptionId, CONVERSIONS)
				.post(conversion, ConversionResult.class);
	}
}
