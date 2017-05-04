package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.Order;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.request.CreateOrderRequest;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionProvisioningState;
import org.springframework.social.partnercenter.http.client.RestResource;

public class OrderTemplate extends PagingResourceTemplate<Order> implements OrderOperations {
	private final RestResource restResource;

	public OrderTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Order>> getACustomersOrders(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "orders")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Order>>() {});
	}

	@Override
	public ResponseEntity<Order> getById(String customerId, String orderId) {
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.get(Order.class);
	}

	@Override
	public ResponseEntity<Order> createAddOnOrder(String customerId, String orderId, CreateOrderRequest order) {
		return restResource.request()
				.pathSegment(customerId, "orders", orderId)
				.patch(order, Order.class);
	}

	@Override
	public ResponseEntity<Order> createOrder(String customerId, CreateOrderRequest request) {
		return restResource.request()
				.pathSegment(customerId, "orders")
				.post(request, Order.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}

	@Override
	public ResponseEntity<SubscriptionProvisioningState> getSubscriptionProvisioningState(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId, "provisioningstatus")
				.get(SubscriptionProvisioningState.class);
	}
}
