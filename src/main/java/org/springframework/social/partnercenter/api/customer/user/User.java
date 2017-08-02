package org.springframework.social.partnercenter.api.customer.user;

import java.util.Map;

import org.springframework.social.partnercenter.api.customer.PasswordProfile;

public class User {
	private String id;
	private String userPrincipalName;
	private String firstName;
	private String lastName;
	private String displayName;
	private PasswordProfile passwordProfile;
	private String lastDirectorySyncTime;
	private Map<String, String> attributes;
	private String userDomainType;
	private UserState state;
	private String softDeletionTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLastDirectorySyncTime() {
		return lastDirectorySyncTime;
	}

	public void setLastDirectorySyncTime(String lastDirectorySyncTime) {
		this.lastDirectorySyncTime = lastDirectorySyncTime;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getUserDomainType() {
		return userDomainType;
	}

	public void setUserDomainType(String userDomainType) {
		this.userDomainType = userDomainType;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public String getSoftDeletionTime() {
		return softDeletionTime;
	}

	public void setSoftDeletionTime(String softDeletionTime) {
		this.softDeletionTime = softDeletionTime;
	}
}
