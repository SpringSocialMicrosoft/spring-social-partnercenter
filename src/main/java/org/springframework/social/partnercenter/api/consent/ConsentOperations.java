package org.springframework.social.partnercenter.api.consent;

import org.springframework.http.ResponseEntity;

public interface ConsentOperations {
    ResponseEntity<Void> getConsent(String customerTenantId, String multiTenantApplicationId);

    ResponseEntity<Void> createConsent(String customerTenantId, Consent consent);

    ResponseEntity deleteConsent(String customerTenantId, String multiTenantApplicationId);
}
