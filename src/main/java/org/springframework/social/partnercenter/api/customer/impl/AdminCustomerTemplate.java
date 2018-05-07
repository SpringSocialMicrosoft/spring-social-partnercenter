package org.springframework.social.partnercenter.api.customer.impl;

import static org.springframework.social.partnercenter.api.customer.query.Operator.STARTS_WITH;
import static org.springframework.social.partnercenter.serialization.Json.toJson;
import static org.springframework.util.Assert.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;
import org.springframework.social.partnercenter.api.customer.SubscribedSku;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminCustomerTemplate extends CustomerTemplate implements AdminCustomerOperations {
	private RestResource restResource;

	public AdminCustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String domain) {
		notNull(domain, "[Assertion failed] - domain argument must be null");
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", toJson(Filter.builder().field("Domain").operator(STARTS_WITH).value(domain).build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<SubscribedSku>> subscribedSkus(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "subscribedskus")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<SubscribedSku>>() {});
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerId, String etag, CustomerBillingProfile billingProfile) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(etag, "[Assertion failed] - etag argument must be null");
		notNull(billingProfile, "[Assertion failed] - billingProfile argument must be null");
		return  restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.header("If-Match", etag)
				.put(billingProfile, CustomerBillingProfile.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
