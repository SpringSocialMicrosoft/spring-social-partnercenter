package org.springframework.social.partnercenter.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionProvisioningState;

public interface AdminOrderOperations extends OrderOperations {
	ResponseEntity<SubscriptionProvisioningState> getSubscriptionProvisioningState(String customerTenantId, String subscriptionId);
}
