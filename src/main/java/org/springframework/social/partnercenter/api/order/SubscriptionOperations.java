package org.springframework.social.partnercenter.api.order;

import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;

public interface SubscriptionOperations {
	Subscription getById(String resellerCid, String id);
	GetSubscriptionListResponse getCustomersSubscriptions(String customerId);
	GetSubscriptionListResponse getSubscriptionsByOrderId(String customerId, String orderId);
	GetSubscriptionListResponse getAddOnsForBySubscriptionId(String customerId, String subscriptionId);
	Subscription updateSubscription(String customerId, String subscriptionId, Subscription subscription);
	String getSubscriptionUpgrade(String customerId, String subscriptionId);
	String upgradeSubscription(String customerId, String targetSubscription, Subscription target);
}
