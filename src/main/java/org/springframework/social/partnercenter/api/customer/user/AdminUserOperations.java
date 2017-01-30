package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.request.AssignLicensesToUserRequest;
import org.springframework.social.partnercenter.api.customer.user.request.CreateUserAccountsForCustomerRequest;

public interface AdminUserOperations extends UserOperations{
	ResponseEntity<String> assignLicensesToUser(String customerId, String userId, AssignLicensesToUserRequest request);
	ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId);
	ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CreateUserAccountsForCustomerRequest request);
	ResponseEntity deleteUserAccountsForCustomer(String customerTenantId);
}
