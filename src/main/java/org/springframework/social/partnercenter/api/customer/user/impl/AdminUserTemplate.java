package org.springframework.social.partnercenter.api.customer.user.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.CustomerUser;
import org.springframework.social.partnercenter.api.customer.user.License;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminUserTemplate extends UserTemplate implements AdminUserOperations {
	private final RestResource restResource;

	public AdminUserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<String> assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request) {
		return restResource.request()
				.pathSegment(customerId, "users", userId, "licenseupdates")
				.post(request, String.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "licenses")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<License>>() {});
	}

	@Override
	public ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CustomerUser request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, CustomerUser.class);
	}

	@Override
	public ResponseEntity deleteUserAccountsForCustomer(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.delete();
	}

	@Override
	public ResponseEntity<CustomerUser> getUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.get(CustomerUser.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<CustomerUser>> getUsers(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<CustomerUser>>() {});
	}

	@Override
	public ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users")
				.post(request, CustomerUser.class);
	}

	@Override
	public ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.patch(request, CustomerUser.class);
	}

	@Override
	public ResponseEntity deleteUser(String customerTenantId, String userId) {
		return restResource.request().pathSegment(customerTenantId, "users", userId).delete();
	}

	@Override
	public ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, CustomerUser request) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId)
				.patch(request, CustomerUser.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getUserRoles(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "directoryroles")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getAllRoles(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", "directoryroles")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getRolesByRoleId(String customerTenantId, String roleId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", roleId, "directoryroles")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
