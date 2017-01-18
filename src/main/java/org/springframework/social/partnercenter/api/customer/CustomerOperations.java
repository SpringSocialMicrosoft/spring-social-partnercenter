package org.springframework.social.partnercenter.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.response.CustomerListResponse;
import org.springframework.social.partnercenter.api.customer.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;

public interface CustomerOperations {
	ResponseEntity<Customer> create(Customer customer);
	ResponseEntity<CustomerRelationshipRequest> requestResellerRelationship();
    ResponseEntity<Customer> getById(String tenantId);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String filter);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByCompanyName(int size, String filter);
	ResponseEntity<CustomerListResponse> getList(int size);
    ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId);
	ResponseEntity<CompanyProfile> getCustomersCompanyProfile(String customerId);
    ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerId, String etag, CustomerBillingProfile billingProfile);
    ResponseEntity<Subscription> updateFriendlyName(String customerTenantId, String subscriptionId, String nickname);
}
