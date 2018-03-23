package org.springframework.social.partnercenter.api.customer.user;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceBase;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public final class UserMember extends ResourceBase {
	private String id;
	private String displayName;
	private String userPrincipalName;
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String id;
		private String displayName;
		private String userPrincipalName;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder userPrincipalName(String userPrincipalName) {
			this.userPrincipalName = userPrincipalName;
			return this;
		}

		public UserMember build() {
			UserMember userMember = new UserMember();
			userMember.setId(id);
			userMember.setDisplayName(displayName);
			userMember.setUserPrincipalName(userPrincipalName);
			return userMember;
		}
	}
}
