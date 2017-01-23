package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import java.util.UUID;

import org.springframework.social.partnercenter.api.ApiFault;

public class UserLicenseError {
	private String email;
	private String name;
	private UUID userObjectId;
	private ApiFault errors;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUserObjectId() {
		return userObjectId;
	}

	public void setUserObjectId(UUID userObjectId) {
		this.userObjectId = userObjectId;
	}

	public ApiFault getErrors() {
		return errors;
	}

	public void setErrors(ApiFault errors) {
		this.errors = errors;
	}
}
