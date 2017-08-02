package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleListResponse;

public interface UserOperations {
	ResponseEntity<CustomerUser> createUser(String customerTenantId, CreateUserRequest request);
	ResponseEntity<CustomerUser> createUser(String customerTenantId, CreateUserRequest request, String userId);
	ResponseEntity deleteUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request);
	ResponseEntity<GetRoleListResponse> getUserRoles(String customerTenantId, String userId);
	ResponseEntity<GetRoleListResponse> getAllRoles(String customerTenantId);
	ResponseEntity<GetRoleListResponse> getRolesByRoleId(String customerTenantId, String RoleId);

}
