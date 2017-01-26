package org.springframework.social.partnercenter.api.admin.customer.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.admin.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminCustomerTemplate extends AbstractTemplate implements AdminCustomerOperations {
	private RestResource restResource;

	public AdminCustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(CustomerBillingProfile.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
