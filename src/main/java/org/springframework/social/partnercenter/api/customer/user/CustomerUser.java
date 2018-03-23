package org.springframework.social.partnercenter.api.customer.user;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.customer.PasswordProfile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class CustomerUser extends User{
	private String usageLocation;


	public String getUsageLocation() {
		return usageLocation;
	}

	public CustomerUser setUsageLocation(String usageLocation) {
		this.usageLocation = usageLocation;
		return this;
	}

	@JsonIgnore
	public static Builder builder(){
		return new Builder();
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
		private String usageLocation;

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

		public Builder usageLocation(String usageLocation) {
			this.usageLocation = usageLocation;
			return this;
		}

		public CustomerUser build(){
			CustomerUser user = new CustomerUser();
			user.setId(id);
			user.setAttributes(attributes);
			user.setDisplayName(displayName);
			user.setUsageLocation(usageLocation);
			user.setFirstName(firstName);
			user.setLastDirectorySyncTime(lastDirectorySyncTime);
			user.setUserPrincipalName(userPrincipalName);
			user.setLastName(lastName);
			user.setUserDomainType(userDomainType);
			user.setPasswordProfile(passwordProfile);
			user.setState(state);
			user.setSoftDeletionTime(softDeletionTime);
			return user;
		}
	}
}
