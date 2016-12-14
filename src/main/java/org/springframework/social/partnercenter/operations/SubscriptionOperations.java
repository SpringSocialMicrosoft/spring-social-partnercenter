package org.springframework.social.partnercenter.operations;

import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.response.GetSubscriptionsResponse;

public interface SubscriptionOperations {
	Subscription getById(String resellerCid, String id);
	GetSubscriptionsResponse getCustomersSubscriptions(String customerTenantId);
}
