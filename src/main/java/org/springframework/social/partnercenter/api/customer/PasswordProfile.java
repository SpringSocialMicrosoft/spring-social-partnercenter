package org.springframework.social.partnercenter.api.customer;

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
}
