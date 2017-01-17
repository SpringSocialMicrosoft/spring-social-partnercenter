package org.springframework.social.partnercenter.api.customer.user.impl;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.User;
import org.springframework.social.partnercenter.api.customer.request.CreateUserRequest;
import org.springframework.social.partnercenter.api.customer.request.UpdateUserPasswordRequest;
import org.springframework.social.partnercenter.api.customer.response.GetRoleResponse;
import org.springframework.social.partnercenter.api.customer.user.CustomerUser;
import org.springframework.social.partnercenter.api.customer.user.License;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UserTemplate extends AbstractTemplate implements UserOperations{
	private RestResource restResource;

	public UserTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public void assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request) {
		restResource.request()
				.pathSegment("users", userId, "licenseupdates")
				.post(customerId, String.class);
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
	public PartnerCenterResponse<License> getLicensesAssignToAUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "licenses")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<License>>() {});
	}

	@Override
	public CustomerUser createUserAccountsForCustomer(String customerTenantId, CreateUserAccountsForCustomerRequest request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, CustomerUser.class);
	}

	@Override
	public void deleteUserAccountsForCustomer(String customerTenantId) {
		restResource.request()
				.pathSegment(customerTenantId, "users")
				.delete();
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
