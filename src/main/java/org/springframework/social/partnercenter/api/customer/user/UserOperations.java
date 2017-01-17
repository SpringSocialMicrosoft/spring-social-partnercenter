package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.User;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;

public interface UserOperations {
	void assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request);

	User createUser(String customerTenantId, CreateUserRequest request);
	User createUser(String customerTenantId, CreateUserRequest request, String userId);
	void deleteUser(String customerTenantId, String userId);
	User getUser(String customerTenantId, String userId);
	User updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request);
	PartnerCenterResponse<Role> getUserRoles(String customerTenantId, String userId);
	PartnerCenterResponse<Role> getAllRoles(String customerTenantId);
	PartnerCenterResponse<Role> getRolesByRoleId(String customerTenantId, String RoleId);
	PartnerCenterResponse<License> getLicensesAssignToAUser(String customerTenantId, String userId);
	CustomerUser createUserAccountsForCustomer(String customerTenantId, CreateUserAccountsForCustomerRequest request);
	void deleteUserAccountsForCustomer(String customerTenantId);
}
