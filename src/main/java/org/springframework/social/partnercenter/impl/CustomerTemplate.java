package org.springframework.social.partnercenter.impl;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.List;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestService;
import org.springframework.social.partnercenter.model.customer.BillingProfile;
import org.springframework.social.partnercenter.model.customer.Customer;
import org.springframework.social.partnercenter.model.customer.Role;
import org.springframework.social.partnercenter.model.customer.User;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.request.CreateUserRequest;
import org.springframework.social.partnercenter.model.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.model.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.model.response.GetRoleResponse;
import org.springframework.social.partnercenter.model.response.PartnerCenterResponse;
import org.springframework.social.partnercenter.operations.CustomerOperations;
import org.springframework.social.partnercenter.uri.CustomerUriProvider;
import org.springframework.social.partnercenter.uri.SubscriptionUriProvider;
import org.springframework.social.partnercenter.uri.UriProvider;

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
		return restService.get(CustomerUriProvider.buildCustomerUri(tenantId), Customer.class).getBody();
	}

	@Override
	public List<Customer> getByCompanyNameOrDomain(int size, String filter) {
		checkAuthorization();
		return asList(restService.get(CustomerUriProvider.buildGetByCompanyNameOrDomainUri(size, filter), Customer[].class).getBody());
	}

	@Override
	public List<Customer> getList(int size) {
		checkAuthorization();
		return asList(restService.get(CustomerUriProvider.buildGetListUri(size), Customer[].class).getBody());
}

	@Override
	public BillingProfile getBillingProfile(String customerId) {
		checkAuthorization();
		return restService.get(CustomerUriProvider.buildBillingProfileUri(customerId), BillingProfile.class).getBody();
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
				Subscription.class).getBody();
	}

	private Subscription getPartnerCenterSubscription(String customerTenantId, String subscriptionId) {
		URI getSubscriptionUri = SubscriptionUriProvider.buildSubscriptionUri(customerTenantId, subscriptionId);
		return restService.get(getSubscriptionUri, Subscription.class).getBody();
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users").build().toUri();
		return restService.post(usersUri, request, User.class).getBody();
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request, String userId) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerTenantId, "users", userId)
				.build().toUri();
		return restService.post(usersUri, request, User.class).getBody();
	}

	@Override
	public void deleteUser(String customerTenantId, String userId) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerTenantId, "users", userId)
				.build().toUri();
		restService.delete(usersUri);
	}

	@Override
	public User getUser(String customerTenantId, String userId) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users", userId).build().toUri();
		return restService.get(usersUri, User.class).getBody();
	}

	@Override
	public User updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users", userId).build().toUri();
		return restService.post(usersUri, request, User.class).getBody();
	}

	@Override
	public GetRoleResponse getUserRoles(String customerTenantId, String userId) {
		URI uri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users", userId, "directoryroles")
				.build().toUri();
		return restService.get(uri, GetRoleResponse.class).getBody();
	}

	@Override
	public PartnerCenterResponse<Role> getAllRoles(String customerTenantId) {
		URI uri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users", "directoryroles")
				.build().toUri();
		return restService.get(uri, GetRoleResponse.class).getBody();
	}

	@Override
	public PartnerCenterResponse<Role> getRolesByRoleId(String customerTenantId, String roleId) {
		URI uri = UriProvider.partnerCenterCustomerApiBuilder().pathSegment(customerTenantId, "users", roleId, "directoryroles")
				.build().toUri();
		return restService.get(uri, GetRoleResponse.class).getBody();
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
