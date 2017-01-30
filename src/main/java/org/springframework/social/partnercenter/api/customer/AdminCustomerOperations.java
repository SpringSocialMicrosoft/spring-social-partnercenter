package org.springframework.social.partnercenter.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AdminCustomerOperations extends CustomerOperations{
	ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String filter);
}
