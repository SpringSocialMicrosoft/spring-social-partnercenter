package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.ResourceLinks;
import org.springframework.social.partnercenter.api.customer.PasswordProfile;

public class User {
	private String id;
	private String userPrincipalName;
	private String firstName;
	private String lastName;
	private String displayName;
	private PasswordProfile passwordProfile;
	private String lastDirectorySyncTime;
	private ResourceAttributes attributes;
	private String userDomainType;
	private UserState state;
	private String softDeletionTime;
	private ResourceLinks links;

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

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
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

	public ResourceLinks getLinks() {
		return links;
	}

	public void setLinks(ResourceLinks links) {
		this.links = links;
	}

	public static class Builder {
		private String id;
		private String userPrincipalName;
		private String firstName;
		private String lastName;
		private String displayName;
		private PasswordProfile passwordProfile;
		private String lastDirectorySyncTime;
		private ResourceAttributes attributes;
		private String userDomainType;
		private UserState state;
		private String softDeletionTime;
		private ResourceLinks links;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder userPrincipalName(String userPrincipalName) {
			this.userPrincipalName = userPrincipalName;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder passwordProfile(PasswordProfile passwordProfile) {
			this.passwordProfile = passwordProfile;
			return this;
		}

		public Builder lastDirectorySyncTime(String lastDirectorySyncTime) {
			this.lastDirectorySyncTime = lastDirectorySyncTime;
			return this;
		}

		public Builder attributes(ResourceAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public Builder userDomainType(String userDomainType) {
			this.userDomainType = userDomainType;
			return this;
		}

		public Builder state(UserState state) {
			this.state = state;
			return this;
		}

		public Builder softDeletionTime(String softDeletionTime) {
			this.softDeletionTime = softDeletionTime;
			return this;
		}

		public Builder links(ResourceLinks links) {
			this.links = links;
			return this;
		}
	}
}
