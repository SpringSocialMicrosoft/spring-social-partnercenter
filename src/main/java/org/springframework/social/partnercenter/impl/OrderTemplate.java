package org.springframework.social.partnercenter.impl;

import org.springframework.social.partnercenter.operations.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestService;
import org.springframework.social.partnercenter.uri.UriProvider;
import org.springframework.social.partnercenter.model.order.Order;

public class OrderTemplate extends AbstractTemplate implements OrderOperations {
	private final RestService restService;

	protected OrderTemplate(RestService restService, boolean isAuthorized) {
		super(isAuthorized);
		this.restService = restService;
	}

	@Override
	public Order getById(String customerId, String orderId) {
		return this.restService.get(UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerId, "orders", orderId).
						build().toUri(),
				Order.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
