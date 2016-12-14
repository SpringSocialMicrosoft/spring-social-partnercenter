package org.springframework.social.partnercenter.impl;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.List;

import org.springframework.social.partnercenter.operations.CustomerOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestService;
import org.springframework.social.partnercenter.uri.CustomerUriProvider;
import org.springframework.social.partnercenter.uri.SubscriptionUriProvider;
import org.springframework.social.partnercenter.model.customer.BillingProfile;
import org.springframework.social.partnercenter.model.customer.Customer;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.response.CustomerRelationshipRequest;

public class CustomerTemplate extends AbstractTemplate implements CustomerOperations {
	private RestService restService;

	protected CustomerTemplate(RestService restService, boolean isAuthorized) {
		super(isAuthorized);
		this.restService = restService;
	}

	@Override
	public Customer create(Customer customer) {
		return null;
	}

	@Override
	public CustomerRelationshipRequest requestResellerRelationship() {
		return null;
	}

	@Override
	public Customer getById(String tenantId) {
		return restService.get(CustomerUriProvider.buildBetByIdUri(tenantId), Customer.class);
	}

	@Override
	public List<Customer> getByCompanyNameOrDomain(int size, String filter) {
		checkAuthorization();
		return asList(restService.get(CustomerUriProvider.buildGetByCompanyNameOrDomainUri(size, filter), Customer[].class));
	}

	@Override
	public List<Customer> getList(int size) {
		checkAuthorization();
		return asList(restService.get(CustomerUriProvider.buildGetListUri(size), Customer[].class));
	}

	@Override
	public BillingProfile getBillingProfile(String customerId) {
		checkAuthorization();
		return restService.get(CustomerUriProvider.buildBillingProfileUri(customerId), BillingProfile.class);
	}

	@Override
	public BillingProfile updateBillingProfile(String customerId, String etag, BillingProfile billingProfile) {
		checkAuthorization();
		return restService.newRequest()
				.setHeader("If-Match", etag)
				.put(CustomerUriProvider.buildBillingProfileUri(customerId), billingProfile, BillingProfile.class);
	}

	@Override
	public Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname) {
		Subscription subscription = getPartnerCenterSubscription(customerTenantId, subscriptionId);
		subscription.setFriendlyName(nickname);
		return restService.post(
				SubscriptionUriProvider.buildSubscriptionUri(customerTenantId, subscriptionId),
				subscription,
				Subscription.class);
	}

	private Subscription getPartnerCenterSubscription(String customerTenantId, String subscriptionId) {
		URI getSubscriptionUri = SubscriptionUriProvider.buildSubscriptionUri(customerTenantId, subscriptionId);
		return restService.get(getSubscriptionUri, Subscription.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
