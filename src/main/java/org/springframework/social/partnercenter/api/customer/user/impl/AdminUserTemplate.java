package org.springframework.social.partnercenter.api.customer.user.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.CustomerUser;
import org.springframework.social.partnercenter.api.customer.user.License;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminUserTemplate extends UserTemplate implements AdminUserOperations {
	private final RestResource restResource;

	protected AdminUserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<String> assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request) {
		return restResource.request()
				.pathSegment("users", userId, "licenseupdates")
				.post(customerId, String.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId) {
		return restResource.request()
				.pathSegment(customerTenantId, "users", userId, "licenses")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<License>>() {});
	}

	@Override
	public ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CreateUserAccountsForCustomerRequest request) {
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
	protected String getProviderId() {
		return PartnerCenterAdmin.PROVIDER_ID;
	}
}
