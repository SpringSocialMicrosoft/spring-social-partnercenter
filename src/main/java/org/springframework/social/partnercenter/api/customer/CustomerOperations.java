package org.springframework.social.partnercenter.api.customer;

import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;

public interface CustomerOperations {
	Customer create(Customer customer);
    CustomerRelationshipRequest requestResellerRelationship();
    Customer getById(String tenantId);
	PartnerCenterResponse<Customer> getCompanyByDomain(int size, String filter);
	PartnerCenterResponse<Customer> getCompanyByCompanyName(int size, String filter);
	PartnerCenterResponse<Customer> getList(int size);
    CustomerBillingProfile getBillingProfile(String customerId);
	CompanyProfile getCustomersCompanyProfile(String customerId);
    CustomerBillingProfile updateBillingProfile(String customerId, String etag, CustomerBillingProfile billingProfile);
    Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname);
}
