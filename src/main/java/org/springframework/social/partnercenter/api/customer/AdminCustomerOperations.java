package org.springframework.social.partnercenter.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.query.Operator;

public interface AdminCustomerOperations extends CustomerOperations{
	ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerTenantId);
	ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerTenantId, String etag, CustomerBillingProfile billingProfile);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String filter);
	ResponseEntity<PartnerCenterResponse<SubscribedSku>> subscribedSkus(String customerId);
	ResponseEntity<PartnerCenterResponse<Customer>> getCustomersOfAnIndirectReseller(String resellerID, int offset, int size);
	ResponseEntity<PartnerCenterResponse<Customer>> queryCustomers(int size, CustomerSearchField customerSearchField, Operator operator, String targetValue);
}
