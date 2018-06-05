package org.springframework.social.partnercenter.api.customer.impl;

import static org.springframework.social.partnercenter.api.customer.CustomerSearchField.DOMAIN;
import static org.springframework.social.partnercenter.api.customer.CustomerSearchField.INDIRECT_RESELLER;
import static org.springframework.social.partnercenter.api.query.Operator.STARTS_WITH;
import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;
import org.springframework.social.partnercenter.api.customer.CustomerSearchField;
import org.springframework.social.partnercenter.api.customer.SubscribedSku;
import org.springframework.social.partnercenter.api.query.Filter;
import org.springframework.social.partnercenter.api.query.Operator;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminCustomerTemplate extends CustomerTemplate implements AdminCustomerOperations {
	private RestResource restResource;

	public AdminCustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String domain) {
		notNull(domain, "domain");
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Filter.create(DOMAIN, STARTS_WITH, domain))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<SubscribedSku>> subscribedSkus(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(customerId, "subscribedskus")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<SubscribedSku>>() {});
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerId, String etag, CustomerBillingProfile billingProfile) {
		notNull(customerId, "customerId");
		notNull(etag, "etag");
		notNull(billingProfile, "billingProfile");
		return  restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.header("If-Match", etag)
				.put(billingProfile, CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCustomersOfAnIndirectReseller(String resellerID, int offset, int size) {
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Filter.create(INDIRECT_RESELLER, STARTS_WITH, resellerID))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {
				});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> queryCustomers(int size, CustomerSearchField customerSearchField, Operator operator, String targetValue) {
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Filter.create(customerSearchField, operator, targetValue))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});

	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
