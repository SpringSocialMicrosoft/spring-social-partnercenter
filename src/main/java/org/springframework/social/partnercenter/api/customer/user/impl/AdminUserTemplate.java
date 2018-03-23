package org.springframework.social.partnercenter.api.customer.user.impl;

import static org.springframework.social.partnercenter.serialization.Json.toJson;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Role;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.api.customer.query.Operator;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.CustomerUser;
import org.springframework.social.partnercenter.api.customer.user.License;
import org.springframework.social.partnercenter.api.customer.user.UserMember;
import org.springframework.social.partnercenter.api.customer.user.request.CustomerUserAssignLicenses;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminUserTemplate extends UserTemplate implements AdminUserOperations {
	private static final String DIRECTORY_ROLES = "directoryroles";
	private static final String USER_MEMBERS = "usermembers";
	private final RestResource restResource;

	public AdminUserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<String> assignLicensesToUser(String customerId, String userId, CustomerUserAssignLicenses request) {
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
	public ResponseEntity<UserMember> setUserRole(String customerTenantId, String roleId, UserMember userMember) {
		return restResource.request()
				.pathSegment(customerTenantId, DIRECTORY_ROLES, roleId, USER_MEMBERS)
				.post(userMember, UserMember.class);
	}

	@Override
	public ResponseEntity removeUserRole(String customerTenantId, String roleId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, DIRECTORY_ROLES, roleId, USER_MEMBERS, userId)
				.delete();
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getUserRoles(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, DIRECTORY_ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getAllRoles(String customerTenantId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", DIRECTORY_ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Role>> getRolesByRoleId(String customerTenantId, String roleId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", roleId, DIRECTORY_ROLES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Role>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "users")
				.queryParam("filter", toJson(Filter.builder().field("UserState").operator(Operator.EQUALS).value("Inactive").build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<CustomerUser>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId, Integer size) {
		return restResource.request()
				.pathSegment(customerId, "users")
				.queryParam("size", size)
				.queryParam("filter", toJson(Filter.builder().field("UserState").operator(Operator.EQUALS).value("Inactive").build()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<CustomerUser>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
