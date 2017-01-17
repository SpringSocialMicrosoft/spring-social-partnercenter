package org.springframework.social.partnercenter.api.customer.user.request;

import java.util.Map;

import org.springframework.social.partnercenter.api.customer.PasswordProfile;

public class CreateUserAccountsForCustomerRequest {
	private String usageLocation;
	private String userPrincipalName;
	private String firstName;
	private String lastName;
	private String displayName;
	private PasswordProfile passwordProfile;
	private Map<String, String> attributes;

	public String getUsageLocation() {
		return usageLocation;
	}

	public void setUsageLocation(String usageLocation) {
		this.usageLocation = usageLocation;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

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
}
