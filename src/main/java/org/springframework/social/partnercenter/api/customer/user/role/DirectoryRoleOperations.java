package org.springframework.social.partnercenter.api.customer.user.role;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.UserMember;

public interface DirectoryRoleOperations extends PagingResourceOperations<DirectoryRole> {
	ResponseEntity<PartnerCenterResponse<DirectoryRole>> getDirectoryRoles(String customerTenantId);
	/**
	 * Get a list of all the roles/permissions attached to a user account. Variations include getting a list of all permissions across all user accounts for a customer, and getting a list of users that have a given role.
	 * @param customerTenantId The value is a GUID formatted customer-id that identifies the customer.
	 * @param userId The value is a GUID formatted user-id that belongs to a single user account.
	 * @return List of {@link Role}
	 */
	ResponseEntity<PartnerCenterResponse<DirectoryRole>> getUserRoles(String customerTenantId, String userId);
	ResponseEntity<PartnerCenterResponse<UserMember>> getUserMembersWithRole(String customerTenantId, String roleId);
	/**
	 * How to remove a user from a directory role within a customer account.
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @param roleId The value is a GUID formatted role-id that identifies the role to remove from the user.
	 * @param memberId The value is a GUID formatted user-id that belongs to a single user account.
	 * @return 204 No Content
	 */
	ResponseEntity removeRole(String customerId, String roleId, String memberId);
	/**
	 * Within a customer account, there's a set of directory roles. You can assign user accounts to those roles.
	 * @param customerTenantId The value is a GUID formatted customer-id that identifies the customer.
	 * @param roleId The value is a GUID formatted role-id that identifies the role to assign to the user.
	 * @param userMember The User who will be assigned the given role
	 * @return userMember {@link UserMember}
	 */
	ResponseEntity<UserMember> addRoleToMember(String customerTenantId, String roleId, UserMember userMember);
}
