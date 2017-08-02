package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleListResponse;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;

public interface AdminUserOperations extends UserOperations{
	ResponseEntity<String> assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request);
	ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CreateUserAccountsForCustomerRequest request);
	ResponseEntity deleteUserAccountsForCustomer(String customerTenantId);
	ResponseEntity<CustomerUser> getUser(String customerTenantId, String userId);
	ResponseEntity<PartnerCenterResponse<CustomerUser>> getUsers(String customerTenantId);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CreateUserRequest request);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CreateUserRequest request, String userId);
	ResponseEntity deleteUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request);
	ResponseEntity<GetRoleListResponse> getUserRoles(String customerTenantId, String userId);
	ResponseEntity<GetRoleListResponse> getAllRoles(String customerTenantId);
	ResponseEntity<GetRoleListResponse> getRolesByRoleId(String customerTenantId, String RoleId);
}
