package org.springframework.social.partnercenter.api.customer;

import java.util.List;

import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;

public interface CustomerOperations {
	Customer create(Customer customer);
    CustomerRelationshipRequest requestResellerRelationship();
    Customer getById(String tenantId);
	List<Customer> getByCompanyNameOrDomain(int size, String filter);
	PartnerCenterResponse<Customer> getList(int size);
    BillingProfile getBillingProfile(String customerId);
    BillingProfile updateBillingProfile(String customerId, String etag, BillingProfile billingProfile);
    Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname);
	User createUser(String customerTenantId, CreateUserRequest request);
	User createUser(String customerTenantId, CreateUserRequest request, String userId);
	void deleteUser(String customerTenantId, String userId);
	User getUser(String customerTenantId, String userId);
	User updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request);
	PartnerCenterResponse<Role> getUserRoles(String customerTenantId, String userId);
	PartnerCenterResponse<Role> getAllRoles(String customerTenantId);
	PartnerCenterResponse<Role> getRolesByRoleId(String customerTenantId, String RoleId);
}
