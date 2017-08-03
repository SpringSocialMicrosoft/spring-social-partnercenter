package org.springframework.social.partnercenter.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;

public interface CustomerOperations extends PagingResourceOperations<Customer>{
	ResponseEntity<Customer> create(Customer customer);
    ResponseEntity<Customer> getById(String tenantId);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String filter);
	ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByCompanyName(int size, String filter);
	ResponseEntity<PartnerCenterResponse<Customer>> getList(int size);
	ResponseEntity<CustomerCompanyProfile> getCustomersCompanyProfile(String customerId);
    ResponseEntity<Subscription> updateFriendlyName(String customerTenantId, String subscriptionId, String nickname);
}
