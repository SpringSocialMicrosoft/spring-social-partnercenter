package org.springframework.social.partnercenter.api.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PasswordProfile {
	private String password;
	private boolean forceChangePassword;

	public String getPassword() {
		return password;
	}

	public PasswordProfile setPassword(String password) {
		this.password = password;
		return this;
	}

	public boolean isForceChangePassword() {
		return forceChangePassword;
	}

	public PasswordProfile setForceChangePassword(boolean forceChangePassword) {
		this.forceChangePassword = forceChangePassword;
		return this;
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

		public PasswordProfile build(){
			PasswordProfile profile = new PasswordProfile();
			profile.setForceChangePassword(forceChangePassword);
			profile.setPassword(password);
			return profile;
		}
	}
}
