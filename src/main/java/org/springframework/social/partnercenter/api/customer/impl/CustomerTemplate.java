package org.springframework.social.partnercenter.api.customer.impl;

import static org.springframework.social.partnercenter.api.customer.request.Operator.STARTS_WITH;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.CustomerBillingProfile;
import org.springframework.social.partnercenter.api.customer.CustomerCompanyProfile;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.request.Filter;
import org.springframework.social.partnercenter.api.customer.response.CustomerListResponse;
import org.springframework.social.partnercenter.api.customer.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.serialization.Json;

public class CustomerTemplate extends AbstractTemplate implements CustomerOperations {
	private RestResource restResource;

	public CustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<Customer> create(Customer customer) {
		return restResource.request()
				.post(customer, Customer.class);
	}

	@Override
	public ResponseEntity<CustomerRelationshipRequest> requestResellerRelationship() {
		return null;
	}

	@Override
	public ResponseEntity<Customer> getById(String tenantId) {
		return restResource.request()
				.pathSegment(tenantId)
				.get(Customer.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String domain) {
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Json.toJson(Filter.builder().field("Domain").operator(STARTS_WITH).value(domain).build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByCompanyName(int size, String companyName) {
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Json.toJson(Filter.builder().value(companyName).operator(STARTS_WITH).field("CompanyName").build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<CustomerListResponse> getList(int size) {
		return restResource.request()
				.queryParam("size", size)
				.get(CustomerListResponse.class);
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> getBillingProfile(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<CustomerCompanyProfile> getCustomersCompanyProfile(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "profiles", "profiles")
				.get(CustomerCompanyProfile.class);
	}

	@Override
	public ResponseEntity<CustomerBillingProfile> updateBillingProfile(String customerId, String etag, CustomerBillingProfile billingProfile) {
		return  restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.header("If-Match", etag)
				.put(billingProfile, CustomerBillingProfile.class);
	}

	@Override
	public ResponseEntity<Subscription> updateFriendlyName(String customerTenantId, String subscriptionId, String nickname) {
		ResponseEntity<Subscription> subscription = getPartnerCenterSubscription(customerTenantId, subscriptionId);
		subscription.getBody().setFriendlyName(nickname);
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.post(subscription, Subscription.class);
	}

	private ResponseEntity<Subscription> getPartnerCenterSubscription(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.get(Subscription.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
