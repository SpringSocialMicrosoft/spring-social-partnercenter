package org.springframework.social.partnercenter.api.customer.user.role.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.UserMember;
import org.springframework.social.partnercenter.api.customer.user.role.Role;
import org.springframework.social.partnercenter.api.customer.user.role.RoleOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class RoleTemplate extends AbstractTemplate implements RoleOperations{
	private static final String USER_MEMBERS = "usermembers";
	private static final String ROLES = "roles";
	private final RestResource restResource;

	public RoleTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	public ResponseEntity removeUserMember(String memberId) {
		return restResource.request()
				.queryParam("member_id", memberId)
				.delete();
	}

	public ResponseEntity removeUserMember(String memberId, String roleId) {
		return restResource.request()
				.pathSegment(ROLES, roleId, USER_MEMBERS, memberId)
				.delete();
	}

	public ResponseEntity<PartnerCenterResponse<Role>> getRoles() {
		return restResource.request()
				.pathSegment(ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	public ResponseEntity<PartnerCenterResponse<UserMember>> getUserMembersWithRole(String roleId) {
		return restResource.request()
				.pathSegment(ROLES, roleId, USER_MEMBERS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<UserMember>>() {});
	}

	public ResponseEntity<UserMember> addUserMemberToRole(String roleId, UserMember userMember) {
		return restResource.request()
				.queryParam("role_id", roleId)
				.pathSegment("roles", roleId, USER_MEMBERS)
				.post(userMember, UserMember.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
