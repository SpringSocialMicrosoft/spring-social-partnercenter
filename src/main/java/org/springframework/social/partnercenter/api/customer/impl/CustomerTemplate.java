package org.springframework.social.partnercenter.api.customer.impl;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.List;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.customer.BillingProfile;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.User;
import org.springframework.social.partnercenter.api.order.Subscription;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.CustomerListResponse;
import org.springframework.social.partnercenter.api.customer.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleResponse;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.uri.CustomerUriProvider;
import org.springframework.social.partnercenter.api.uri.UriProvider;

public class CustomerTemplate extends AbstractTemplate implements CustomerOperations {
	private RestResource restResource;

	public CustomerTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
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
		return restResource.get(CustomerUriProvider.buildCustomerUri(tenantId), Customer.class);
	}

	@Override
	public List<Customer> getByCompanyNameOrDomain(int size, String filter) {
		checkAuthorization();
		return asList(restResource.request()
				.queryParam("size", size)
				.queryParam("filter", filter)
				.get(Customer[].class));
	}

	@Override
	public PartnerCenterResponse<Customer> getList(int size) {
		checkAuthorization();
		return restResource.request()
				.queryParam("size", size)
				.get(CustomerListResponse.class);
}

	@Override
	public BillingProfile getBillingProfile(String customerId) {
		checkAuthorization();
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(BillingProfile.class);
	}

	@Override
	public BillingProfile updateBillingProfile(String customerId, String etag, BillingProfile billingProfile) {
		checkAuthorization();
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.header("If-Match", etag)
				.put(billingProfile, BillingProfile.class);
	}

	@Override
	public Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname) {
		Subscription subscription = getPartnerCenterSubscription(customerTenantId, subscriptionId);
		subscription.setFriendlyName(nickname);
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.post(subscription, Subscription.class);
	}

	private Subscription getPartnerCenterSubscription(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.get(Subscription.class);
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, User.class);
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class);
	}

	@Override
	public void deleteUser(String customerTenantId, String userId) {
		URI usersUri = UriProvider.partnerCenterCustomerUri()
				.pathSegment(customerTenantId, "users", userId)
				.build().toUri();
		restResource.delete(usersUri);
	}

	@Override
	public User getUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.get(User.class);
	}

	@Override
	public User updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class);
	}

	@Override
	public GetRoleResponse getUserRoles(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "directoryroles")
				.get(GetRoleResponse.class);
	}

	@Override
	public PartnerCenterResponse<Role> getAllRoles(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", "directoryroles")
				.get(GetRoleResponse.class);
	}

	@Override
	public PartnerCenterResponse<Role> getRolesByRoleId(String customerTenantId, String roleId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", roleId, "directoryroles")
				.get(GetRoleResponse.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
