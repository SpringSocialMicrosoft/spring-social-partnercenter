package org.springframework.social.partnercenter.api.customer.request;

import java.util.Map;

import org.springframework.social.partnercenter.api.customer.PasswordProfile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserPasswordRequest {
	private PasswordProfile passwordProfile;
	private Map<String, String> attributes;

	public PasswordProfile getPasswordProfile() {
		return passwordProfile;
	}

	public void setPasswordProfile(PasswordProfile passwordProfile) {
		this.passwordProfile = passwordProfile;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@JsonIgnore
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private String password;
		private boolean forceChangePassword;

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder forceChangePassword(boolean forceChangePassword) {
			this.forceChangePassword = forceChangePassword;
			return this;
		}

		public UpdateUserPasswordRequest build(){
			UpdateUserPasswordRequest request = new UpdateUserPasswordRequest();
			request.setPasswordProfile(PasswordProfile.builder().forceChangePassword(forceChangePassword).password(password).build());
			return request;
		}
	}
}
