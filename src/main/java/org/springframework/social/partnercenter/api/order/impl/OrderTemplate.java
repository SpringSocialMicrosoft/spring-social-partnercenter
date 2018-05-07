package org.springframework.social.partnercenter.api.order.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.Order;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class OrderTemplate extends PagingResourceTemplate<Order> implements OrderOperations {
	private final RestResource restResource;

	public OrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<Order>>() {});
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Order>> getACustomersOrders(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "orders")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Order>>() {});
	}

	@Override
	public ResponseEntity<Order> getById(String customerId, String orderId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(orderId, "[Assertion failed] - orderId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.get(Order.class);
	}

	@Override
	public ResponseEntity<Order> createAddOnOrder(String customerId, String orderId, Order order) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(orderId, "[Assertion failed] - orderId argument must be null");
		notNull(order, "[Assertion failed] - order argument must be null");
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.patch(order, Order.class);
	}

	@Override
	public ResponseEntity<Order> createOrder(String customerId, Order request) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(request, "[Assertion failed] - request argument must be null");
		return restResource.request()
				.pathSegment(customerId, "orders")
				.post(request, Order.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
