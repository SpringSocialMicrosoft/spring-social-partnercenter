package org.springframework.social.partnercenter.api.order.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.response.SubscriptionListResponse;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;

public interface SubscriptionOperations {
	ResponseEntity<Subscription> getById(String resellerCid, String id);
	ResponseEntity<SubscriptionListResponse> getCustomersSubscriptions(String customerId);
	ResponseEntity<SubscriptionListResponse> getSubscriptionsByOrderId(String customerId, String orderId);
	ResponseEntity<SubscriptionListResponse> getAddOnsForBySubscriptionId(String customerId, String subscriptionId);
	ResponseEntity<Subscription> updateSubscription(String customerId, String subscriptionId, Subscription subscription);
	ResponseEntity<Subscription> suspendSubscription(String customerId, String subscriptionId);
	ResponseEntity<Subscription> reactivateSubscription(String customerId, String subscriptionId);
	ResponseEntity<PartnerCenterResponse<Subscription>> getAllSubscriptionsForPartner(String customerId, String mpnId, int offset, int size);
	ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty);
	ResponseEntity<PartnerCenterResponse<Upgrade>> getAvailableUpgrades(String customerId, String subscriptionId);
	ResponseEntity<UpgradeResult> upgradeSubscription(String customerId, String sourceSubscriptionId, Upgrade targetSubscriptionId);
}
