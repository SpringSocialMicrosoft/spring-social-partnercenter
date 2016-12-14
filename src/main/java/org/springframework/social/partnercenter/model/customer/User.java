package org.springframework.social.partnercenter.model.customer;

import java.util.Map;

public class User {
	private String id;
	private String usageLocation;
	private String userPrincipalName;
	private String firstName;
	private String lastName;
	private String displayName;
	private PasswordProfile passwordProfile;
	private String lastDirectorySyncTime;
	private Map<String, String> attributes;
	private String userDomainType;
	private String state;
	private String softDeletionTime;


	public String getUsageLocation() {
		return usageLocation;
	}

	public User setUsageLocation(String usageLocation) {
		this.usageLocation = usageLocation;
		return this;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public User setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public User setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public PasswordProfile getPasswordProfile() {
		return passwordProfile;
	}

	public User setPasswordProfile(PasswordProfile passwordProfile) {
		this.passwordProfile = passwordProfile;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public User setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public String getLastDirectorySyncTime() {
		return lastDirectorySyncTime;
	}

	public User setLastDirectorySyncTime(String lastDirectorySyncTime) {
		this.lastDirectorySyncTime = lastDirectorySyncTime;
		return this;
	}

	public String getUserDomainType() {
		return userDomainType;
	}

	public User setUserDomainType(String userDomainType) {
		this.userDomainType = userDomainType;
		return this;
	}

	public String getState() {
		return state;
	}

	public User setState(String state) {
		this.state = state;
		return this;
	}

	public String getSoftDeletionTime() {
		return softDeletionTime;
	}

	public User setSoftDeletionTime(String softDeletionTime) {
		this.softDeletionTime = softDeletionTime;
		return this;
	}
}
