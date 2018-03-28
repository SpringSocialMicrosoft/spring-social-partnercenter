package org.springframework.social.partnercenter.api.customer.user.role.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.UserMember;
import org.springframework.social.partnercenter.api.customer.user.role.DirectoryRole;
import org.springframework.social.partnercenter.api.customer.user.role.DirectoryRoleOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class DirectoryRoleTemplate extends PagingResourceTemplate<DirectoryRole> implements DirectoryRoleOperations {
	private static final String DIRECTORY_ROLES = "directoryroles";
	private static final String USER_MEMBERS = "usermembers";
	private final RestResource restResource;

	public DirectoryRoleTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<DirectoryRole>>() {});
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<DirectoryRole>> getDirectoryRoles(String customerId) {
		return restResource.request()
				.pathSegment(customerId, DIRECTORY_ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<DirectoryRole>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<DirectoryRole>> getUserRoles(String customerId, String userId) {
		return restResource.request()
				.pathSegment(customerId, "users", userId, DIRECTORY_ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<DirectoryRole>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UserMember>> getUserMembersWithRole(String customerId, String roleId) {
		return restResource.request()
				.pathSegment(customerId, DIRECTORY_ROLES, roleId, USER_MEMBERS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<UserMember>>() {});
	}

	@Override
	public ResponseEntity removeRole(String customerTenantId, String roleId, String memberId) {
		return restResource.request()
				.pathSegment(customerTenantId, DIRECTORY_ROLES, roleId, USER_MEMBERS, memberId)
				.delete();
	}

	@Override
	public ResponseEntity<UserMember> addRoleToMember(String customerId, String roleId, UserMember userMember) {
		return restResource.request()
				.queryParam("role_id", roleId)
				.pathSegment(customerId, DIRECTORY_ROLES, roleId, USER_MEMBERS)
				.post(userMember, UserMember.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
