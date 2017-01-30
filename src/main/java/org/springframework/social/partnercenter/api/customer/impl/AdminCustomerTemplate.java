package org.springframework.social.partnercenter.api.customer.impl;

import static org.springframework.social.partnercenter.api.customer.request.Operator.STARTS_WITH;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;
import org.springframework.social.partnercenter.api.customer.request.Filter;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.serialization.Json;

public class AdminCustomerTemplate extends CustomerTemplate implements AdminCustomerOperations {
	private RestResource restResource;

	public AdminCustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String domain) {
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Json.toJson(Filter.builder().field("Domain").operator(STARTS_WITH).value(domain).build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
