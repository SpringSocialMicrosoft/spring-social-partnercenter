package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.order.Order;
import org.springframework.social.partnercenter.api.order.request.CreateOrderRequest;

public class OrderTemplate extends AbstractTemplate implements OrderOperations {
	private final RestResource restResource;

	public OrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public PartnerCenterResponse<Order> getACustomersOrder(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "orders")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Order>>() {});
	}

	@Override
	public Order getById(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.get(Order.class);
	}

	@Override
	public Order createAddOnOrder(String customerId, String orderId, CreateOrderRequest order) {
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.patch(order, Order.class);
	}

	@Override
	public Order createOrder(String customerId, CreateOrderRequest request) {
		return restResource.request()
				.pathSegment(customerId, "orders")
				.post(request, Order.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
