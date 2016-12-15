package org.springframework.social.partnercenter.impl;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.List;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.model.customer.BillingProfile;
import org.springframework.social.partnercenter.model.customer.Customer;
import org.springframework.social.partnercenter.model.customer.Role;
import org.springframework.social.partnercenter.model.customer.User;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.model.request.CreateUserRequest;
import org.springframework.social.partnercenter.model.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.model.response.CustomerListResponse;
import org.springframework.social.partnercenter.model.response.CustomerRelationshipRequest;
import org.springframework.social.partnercenter.model.response.GetRoleResponse;
import org.springframework.social.partnercenter.model.response.PartnerCenterResponse;
import org.springframework.social.partnercenter.operations.CustomerOperations;
import org.springframework.social.partnercenter.uri.CustomerUriProvider;
import org.springframework.social.partnercenter.uri.UriProvider;

public class CustomerTemplate extends AbstractTemplate implements CustomerOperations {
	private RestResource restResource;

	protected CustomerTemplate(RestResource restResource, boolean isAuthorized) {
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
		return restResource.get(CustomerUriProvider.buildCustomerUri(tenantId), Customer.class).getBody();
	}

	@Override
	public List<Customer> getByCompanyNameOrDomain(int size, String filter) {
		checkAuthorization();
		return asList(restResource.request()
				.queryParam("size", size)
				.queryParam("filter", filter)
				.get(Customer[].class)
				.getBody());
	}

	@Override
	public PartnerCenterResponse<Customer> getList(int size) {
		checkAuthorization();
		return restResource.request()
				.queryParam("size", size)
				.get(CustomerListResponse.class)
				.getBody();
}

	@Override
	public BillingProfile getBillingProfile(String customerId) {
		checkAuthorization();
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.get(BillingProfile.class)
				.getBody();
	}

	@Override
	public BillingProfile updateBillingProfile(String customerId, String etag, BillingProfile billingProfile) {
		checkAuthorization();
		return restResource.request()
				.pathSegment(customerId, "profiles", "billing")
				.header("If-Match", etag)
				.put(billingProfile, BillingProfile.class)
				.getBody();
	}

	@Override
	public Subscription updateFriendlyName(String customerTenantId, String subscriptionId, String nickname) {
		Subscription subscription = getPartnerCenterSubscription(customerTenantId, subscriptionId);
		subscription.setFriendlyName(nickname);
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.post(subscription, Subscription.class)
				.getBody();
	}

	private Subscription getPartnerCenterSubscription(String customerTenantId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.get(Subscription.class)
				.getBody();
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, User.class)
				.getBody();
	}

	@Override
	public User createUser(String customerTenantId, CreateUserRequest request, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class)
				.getBody();
	}

	@Override
	public void deleteUser(String customerTenantId, String userId) {
		URI usersUri = UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerTenantId, "users", userId)
				.build().toUri();
		restResource.delete(usersUri);
	}

	@Override
	public User getUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.get(User.class)
				.getBody();
	}

	@Override
	public User updateUserPassword(String customerTenantId, String userId, UpdateUserPasswordRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.post(request, User.class)
				.getBody();
	}

	@Override
	public GetRoleResponse getUserRoles(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "directoryroles")
				.get(GetRoleResponse.class)
				.getBody();
	}

	@Override
	public PartnerCenterResponse<Role> getAllRoles(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", "directoryroles")
				.get(GetRoleResponse.class)
				.getBody();
	}

	@Override
	public PartnerCenterResponse<Role> getRolesByRoleId(String customerTenantId, String roleId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", roleId, "directoryroles")
				.get(GetRoleResponse.class)
				.getBody();
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
