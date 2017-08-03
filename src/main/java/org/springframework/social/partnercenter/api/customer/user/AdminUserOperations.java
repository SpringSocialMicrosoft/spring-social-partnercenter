package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;

public interface AdminUserOperations extends UserOperations{
	ResponseEntity<String> assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request);
	ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CustomerUser request);
	ResponseEntity deleteUserAccountsForCustomer(String customerTenantId);
	ResponseEntity<CustomerUser> getUser(String customerTenantId, String userId);
	ResponseEntity<PartnerCenterResponse<CustomerUser>> getUsers(String customerTenantId);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request, String userId);
	ResponseEntity deleteUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, CustomerUser request);
	ResponseEntity<PartnerCenterResponse<Role>> getUserRoles(String customerTenantId, String userId);
	ResponseEntity<PartnerCenterResponse<Role>> getAllRoles(String customerTenantId);
	ResponseEntity<PartnerCenterResponse<Role>> getRolesByRoleId(String customerTenantId, String RoleId);
}
