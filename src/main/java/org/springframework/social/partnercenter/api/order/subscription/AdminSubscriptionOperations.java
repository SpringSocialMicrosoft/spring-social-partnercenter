package org.springframework.social.partnercenter.api.order.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.Upgrade;
import org.springframework.social.partnercenter.api.order.subscription.upgrade.UpgradeResult;

public interface AdminSubscriptionOperations extends SubscriptionOperations{
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
