package org.springframework.social.partnercenter.api.order.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface SubscriptionOperations extends PagingResourceOperations<Subscription>{
	ResponseEntity<Subscription> getById(String customerTenantId, String id);
	ResponseEntity<PartnerCenterResponse<Subscription>> getCustomersSubscriptions(String customerId);
	ResponseEntity<PartnerCenterResponse<Subscription>> getSubscriptionsByOrderId(String customerId, String orderId);
	ResponseEntity<PartnerCenterResponse<Subscription>> getAddOnsForBySubscriptionId(String customerId, String subscriptionId);
	ResponseEntity<Subscription> updateSubscription(String customerId, String subscriptionId, Subscription subscription);
	ResponseEntity<Subscription> suspendSubscription(String customerId, String subscriptionId);
	ResponseEntity<Subscription> reactivateSubscription(String customerId, String subscriptionId);
	ResponseEntity<PartnerCenterResponse<Subscription>> getAllSubscriptionsForPartner(String customerId, String mpnId);

	/**
	 * Updates a subscription to increase or decrease the number of subscriptions.
	 * @param customerId Customer tenant id.
	 * @param subscriptionId Subscription to update
	 * @param qty Number of licenses for subscription.
	 * @return Subscription
	 */
	ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty);
}
