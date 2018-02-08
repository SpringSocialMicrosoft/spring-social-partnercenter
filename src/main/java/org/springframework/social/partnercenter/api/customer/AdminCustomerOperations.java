package org.springframework.social.partnercenter.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AdminCustomerOperations extends CustomerOperations{
	ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerTenantId);
	ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerTenantId, String etag, CustomerBillingProfile billingProfile);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String filter);
	ResponseEntity<PartnerCenterResponse<SubscribedSku>> subscribedSkus(String customerId);
}
