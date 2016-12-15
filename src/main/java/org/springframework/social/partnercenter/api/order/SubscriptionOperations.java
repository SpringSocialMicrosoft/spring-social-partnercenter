package org.springframework.social.partnercenter.api.order;

import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionsResponse;

public interface SubscriptionOperations {
	Subscription getById(String resellerCid, String id);
	GetSubscriptionsResponse getCustomersSubscriptions(String customerTenantId);
}
