package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.user.request.CustomerUserAssignLicenses;

public interface AdminUserOperations extends UserOperations{
	ResponseEntity<String> assignLicensesToUser(String customerId, String userId, CustomerUserAssignLicenses request);
	ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CustomerUser request);
	ResponseEntity deleteUserAccountsForCustomer(String customerTenantId);
	ResponseEntity<CustomerUser> getUser(String customerTenantId, String userId);
	ResponseEntity<PartnerCenterResponse<CustomerUser>> getUsers(String customerTenantId);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request);
	ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request, String userId);
	ResponseEntity deleteUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, CustomerUser request);

	/**
	 * Within a customer account, there's a set of directory roles. You can assign user accounts to those roles.
	 * @param customerTenantId The value is a GUID formatted customer-id that identifies the customer.
	 * @param roleId The value is a GUID formatted role-id that identifies the role to assign to the user.
	 * @param userMember The User who will be assigned the given role
	 * @return userMember {@link UserMember}
	 */
	ResponseEntity<UserMember> setUserRole(String customerTenantId, String roleId, UserMember userMember);

	/**
	 * How to remove a user from a directory role within a customer account.
	 * @param customerTenantId The value is a GUID formatted customer-id that identifies the customer.
	 * @param roleId The value is a GUID formatted role-id that identifies the role to remove from the user.
	 * @param userId The value is a GUID formatted user-id that belongs to a single user account.
	 * @return 204 No Content
	 */
	ResponseEntity removeUserRole(String customerTenantId, String roleId, String userId);

	/**
	 * Get a list of all the roles/permissions attached to a user account. Variations include getting a list of all permissions across all user accounts for a customer, and getting a list of users that have a given role.
	 * @param customerTenantId The value is a GUID formatted customer-id that identifies the customer.
	 * @param userId The value is a GUID formatted user-id that belongs to a single user account.
	 * @return List of {@link Role}
	 */
	ResponseEntity<PartnerCenterResponse<Role>> getUserRoles(String customerTenantId, String userId);

	ResponseEntity<PartnerCenterResponse<Role>> getAllRoles(String customerTenantId);
	ResponseEntity<PartnerCenterResponse<Role>> getRolesByRoleId(String customerTenantId, String RoleId);

	/**
	 * Gets a list of deleted CustomerUser resources for a customer by customer ID.
	 *
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @return List of deleted {@link CustomerUser}
	 */
	ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId);

	/**
	 * Gets a list of deleted CustomerUser resources for a customer by customer ID.
	 *
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @param size The maximum size of the list to be returned
	 * @return List of deleted {@link CustomerUser}
	 */
	ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId, Integer size);
}
