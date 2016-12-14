package org.springframework.social.partnercenter.operations;

import java.util.List;

import org.springframework.social.partnercenter.model.customer.BillingProfile;
import org.springframework.social.partnercenter.model.customer.Customer;
import org.springframework.social.partnercenter.model.customer.Role;
import org.springframework.social.partnercenter.model.customer.User;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.request.CreateUserRequest;
import org.springframework.social.partnercenter.model.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.model.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.model.response.PartnerCenterResponse;

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
