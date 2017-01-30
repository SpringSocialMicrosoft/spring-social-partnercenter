package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.User;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleListResponse;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;

public interface UserOperations {
	ResponseEntity<User> createUser(String customerTenantId, CreateUserRequest request);
	ResponseEntity<User> createUser(String customerTenantId, CreateUserRequest request, String userId);
	ResponseEntity deleteUser(String customerTenantId, String userId);
	ResponseEntity<User> getUser(String customerTenantId, String userId);
	ResponseEntity<User> updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request);
	ResponseEntity<GetRoleListResponse> getUserRoles(String customerTenantId, String userId);
	ResponseEntity<GetRoleListResponse> getAllRoles(String customerTenantId);
	ResponseEntity<GetRoleListResponse> getRolesByRoleId(String customerTenantId, String RoleId);

}
