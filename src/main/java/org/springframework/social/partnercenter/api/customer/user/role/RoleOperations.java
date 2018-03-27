package org.springframework.social.partnercenter.api.customer.user.role;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.UserMember;

public interface RoleOperations {
	ResponseEntity removeUserMember(String memberId);
	ResponseEntity removeUserMember(String memberId, String roleId);
	ResponseEntity<PartnerCenterResponse<Role>> getRoles();
	ResponseEntity<PartnerCenterResponse<UserMember>> getUserMembersWithRole(String roleId);
	ResponseEntity<UserMember> addUserMemberToRole(String roleId, UserMember userMember);
}
