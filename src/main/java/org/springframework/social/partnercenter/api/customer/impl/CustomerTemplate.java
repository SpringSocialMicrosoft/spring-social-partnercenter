package org.springframework.social.partnercenter.api.customer.impl;

import static org.springframework.social.partnercenter.api.customer.query.Operator.STARTS_WITH;
import static org.springframework.util.Assert.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerCompanyProfile;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.serialization.Json;

public class CustomerTemplate extends PagingResourceTemplate<Customer> implements CustomerOperations {
	private RestResource restResource;

	public CustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<Customer> create(Customer customer) {
		notNull(customer, "[Assertion failed] - customer argument must be null");
		return restResource.request()
				.post(customer, Customer.class);
	}

	@Override
	public ResponseEntity<Customer> getById(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment(customerId)
				.get(Customer.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByDomain(int size, String domain) {
		notNull(domain, "[Assertion failed] - domain argument must be null");
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Json.toJson(Filter.builder().field("Domain").operator(STARTS_WITH).value(domain).build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCompanyByCompanyName(int size, String companyName) {
		notNull(companyName, "[Assertion failed] - companyName argument must be null");
		return restResource.request()
				.queryParam("size", size)
				.queryParam("filter", Json.toJson(Filter.builder().value(companyName).operator(STARTS_WITH).field("CompanyName").build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getList(int size) {
		return restResource.request()
				.queryParam("size", size)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	public ResponseEntity<CustomerCompanyProfile> getCustomersCompanyProfile(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "profiles", "profiles")
				.get(CustomerCompanyProfile.class);
	}

	@Override
	public ResponseEntity<Subscription> updateFriendlyName(String customerId, String subscriptionId, String nickname) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(subscriptionId, "[Assertion failed] - subscriptionId argument must be null");
		notNull(nickname, "[Assertion failed] - nickname argument must be null");
		ResponseEntity<Subscription> subscription = getPartnerCenterSubscription(customerId, subscriptionId);
		subscription.getBody().setFriendlyName(nickname);
		return restResource.request()
				.pathSegment(customerId, "subscriptions", subscriptionId)
				.post(subscription, Subscription.class);
	}

	private ResponseEntity<Subscription> getPartnerCenterSubscription(String customerId, String subscriptionId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		notNull(subscriptionId, "[Assertion failed] - subscriptionId argument must be null");
		return restResource.request()
				.pathSegment(customerId, "subscriptions", subscriptionId)
				.get(Subscription.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
