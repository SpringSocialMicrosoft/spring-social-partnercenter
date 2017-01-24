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

	/**
	 * Updates a subscription to increase or decrease the number of subscriptions.
	 * @param customerId Customer tenant id.
	 * @param subscriptionId Subscription to update
	 * @param qty Number of licenses for subscription.
	 * @return Subscription
	 */
	ResponseEntity<Subscription> updateSubscriptionQuantity(String customerId, String subscriptionId, int qty);

	/**
	 * Retrieves a list of offers available fot upgrade
	 * @param customerId Customer tenant id.
	 * @param subscriptionId Subscription to be upgraded
	 * @return Upgrade[]
	 */
	ResponseEntity<PartnerCenterResponse<Upgrade>> getAvailableUpgrades(String customerId, String subscriptionId);

	/**
	 * Upgrades a customer's subscription to a specified target subscription.
	 * @param customerId Customer tenant id.
	 * @param sourceSubscriptionId Subscription to be upgraded.
	 * @param upgrade Chosen offer to upgrade to.
	 * @return UpgradeResult
	 */
	ResponseEntity<UpgradeResult> upgradeSubscription(String customerId, String sourceSubscriptionId, Upgrade upgrade);
}
