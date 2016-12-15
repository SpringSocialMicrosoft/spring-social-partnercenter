package org.springframework.social.partnercenter.api.order;

public interface OrderOperations {
	Order getById(String customerId, String orderId);
}
