package org.springframework.social.partnercenter.impl;

import org.springframework.social.partnercenter.operations.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.uri.UriProvider;
import org.springframework.social.partnercenter.model.order.Order;

public class OrderTemplate extends AbstractTemplate implements OrderOperations {
	private final RestResource restResource;

	protected OrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public Order getById(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.get(Order.class)
				.getBody();
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
