package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.user.request.CustomerUserAssignLicenses;

public interface UserOperations {
    ResponseEntity<String> assignLicensesToUser(String customerId, String userId, CustomerUserAssignLicenses request);
    ResponseEntity<PartnerCenterResponse<License>> getLicensesAssignToAUser(String customerTenantId, String userId);
    ResponseEntity<CustomerUser> createUserAccountsForCustomer(String customerTenantId, CustomerUser request);
    ResponseEntity deleteUserAccountsForCustomer(String customerTenantId);
    ResponseEntity<CustomerUser> getUser(String customerTenantId, String userId);
    ResponseEntity<PartnerCenterResponse<CustomerUser>> getUsers(String customerTenantId);
    ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request);
    ResponseEntity<CustomerUser> updateUser(String customerTenantId, CustomerUser request, String userId);
    ResponseEntity deleteUser(String customerTenantId, String userId);
    ResponseEntity<CustomerUser> updateUserPassword(String customerTenantId, String userId, CustomerUser request);
    /**
     * Gets a list of deleted CustomerUser resources for a customer by customer ID.
     *
     * @param customerId The value is a GUID formatted customer-id that identifies the customer.
     * @return List of deleted {@link CustomerUser}
     */
    ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId);

    /**
     * Gets a list of deleted CustomerUser resources for a customer by customer ID.
     *
     * @param customerId The value is a GUID formatted customer-id that identifies the customer.
     * @param size The maximum size of the list to be returned
     * @return List of deleted {@link CustomerUser}
     */
    ResponseEntity<PartnerCenterResponse<CustomerUser>> getDeletedUsers(String customerId, Integer size);
}
