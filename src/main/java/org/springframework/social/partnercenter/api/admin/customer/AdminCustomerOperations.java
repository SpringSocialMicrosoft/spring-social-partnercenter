package org.springframework.social.partnercenter.api.admin.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;

public interface AdminCustomerOperations {
	ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId);
}
