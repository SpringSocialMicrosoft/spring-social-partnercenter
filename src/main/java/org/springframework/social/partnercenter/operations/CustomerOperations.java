package org.springframework.social.partnercenter.operations;

import java.util.List;

import org.springframework.social.partnercenter.model.customer.BillingProfile;
import org.springframework.social.partnercenter.model.customer.Customer;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.response.CustomerRelationshipRequest;

public interface CustomerOperations {
	Customer create(Customer customer);
    CustomerRelationshipRequest requestResellerRelationship();
    Customer getById(String tenantId);
	List<Customer> getByCompanyNameOrDomain(int size, String filter);
    List<Customer> getList(int size);
    BillingProfile getBillingProfile(String customerId);
    BillingProfile updateBillingProfile(String customerId, String etag, BillingProfile billingProfile);
    Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname);

}
