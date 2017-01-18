package org.springframework.social.partnercenter.api.order.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;

public interface SubscriptionOperations {
	ResponseEntity<Subscription> getById(String resellerCid, String id);
	ResponseEntity<GetSubscriptionListResponse> getCustomersSubscriptions(String customerId);
	ResponseEntity<GetSubscriptionListResponse> getSubscriptionsByOrderId(String customerId, String orderId);
	ResponseEntity<GetSubscriptionListResponse> getAddOnsForBySubscriptionId(String customerId, String subscriptionId);
	ResponseEntity<Subscription> updateSubscription(String customerId, String subscriptionId, Subscription subscription);
	ResponseEntity<Subscription> suspendSubscription(String customerId, String subscriptionId);
	ResponseEntity<Subscription> reactivateSubscription(String customerId, String subscriptionId);
	ResponseEntity<PartnerCenterResponse<Subscription>> getAllSubscriptionsForPartner(String customerId, String mpnId, int offset, int size);
	ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty);
//	ResponseEntity<String> getSubscriptionUpgrade(String customerId, String subscriptionId);
	ResponseEntity<GetSubscriptionListResponse> transitionSubscription(String customerId, String sourceSubscriptionId, Subscription targetSubscriptionId);
}
