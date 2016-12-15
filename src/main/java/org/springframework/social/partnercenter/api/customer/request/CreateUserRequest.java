package org.springframework.social.partnercenter.api.customer.request;

import java.util.Map;

import org.springframework.social.partnercenter.api.customer.PasswordProfile;

public class CreateUserRequest {
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

	public CreateUserRequest setUsageLocation(String usageLocation) {
		this.usageLocation = usageLocation;
		return this;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public CreateUserRequest setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public CreateUserRequest setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public CreateUserRequest setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public CreateUserRequest setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public PasswordProfile getPasswordProfile() {
		return passwordProfile;
	}

	public CreateUserRequest setPasswordProfile(PasswordProfile passwordProfile) {
		this.passwordProfile = passwordProfile;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public CreateUserRequest setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
