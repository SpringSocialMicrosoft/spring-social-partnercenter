package org.springframework.social.partnercenter.api.order;

import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.request.CreateOrderRequest;

public interface OrderOperations {
	PartnerCenterResponse<Order> getACustomersOrder(String customerId);
	Order getById(String customerId, String orderId);
	Order createAddOnOrder(String customerId, String orderId, CreateOrderRequest order);
	Order createOrder(String customerId, CreateOrderRequest request);
}
