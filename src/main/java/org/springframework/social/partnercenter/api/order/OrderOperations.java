package org.springframework.social.partnercenter.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.request.CreateOrderRequest;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionProvisioningState;

public interface 	OrderOperations {
	ResponseEntity<PartnerCenterResponse<Order>> getACustomersOrders(String customerId);
	ResponseEntity<Order> getById(String customerId, String orderId);
	ResponseEntity<Order> createAddOnOrder(String customerId, String orderId, CreateOrderRequest order);
	ResponseEntity<Order> createOrder(String customerId, CreateOrderRequest request);
	ResponseEntity<SubscriptionProvisioningState> getSubscriptionProvisioningState(String customerTenantId, String subscriptionId);
}
