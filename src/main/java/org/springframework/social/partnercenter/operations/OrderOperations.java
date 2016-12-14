package org.springframework.social.partnercenter.operations;

import org.springframework.social.partnercenter.model.order.Order;

public interface OrderOperations {
	Order getById(String customerId, String orderId);
}
