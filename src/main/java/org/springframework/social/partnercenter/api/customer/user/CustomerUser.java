package org.springframework.social.partnercenter.api.customer.user;

public class CustomerUser extends User{
	private String usageLocation;


	public String getUsageLocation() {
		return usageLocation;
	}

	public CustomerUser setUsageLocation(String usageLocation) {
		this.usageLocation = usageLocation;
		return this;
	}
}
