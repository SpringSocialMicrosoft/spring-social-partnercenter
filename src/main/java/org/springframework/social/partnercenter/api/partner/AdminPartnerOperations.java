package org.springframework.social.partnercenter.api.partner;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;

public interface AdminPartnerOperations {
	ResponseEntity<PartnerCenterResponse<Subscription>> getSubscriptionsByPartner(String customerId, String mpnId, int offset, int size);
	ResponseEntity<PartnerCenterResponse<Customer>> getCustomersOfAnIndirectReseller(String resellerID, int offset, int size);
}
