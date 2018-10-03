package org.springframework.social.partnercenter.api.consent;

import org.springframework.http.ResponseEntity;

public interface ConsentOperations {
    ResponseEntity<Consent> getConsent(String customerTenantId, String multiTenantApplicationId);

    ResponseEntity<Consent> createConsent(String customerTenantId, Consent consent);

    ResponseEntity deleteConsent(String customerTenantId, String multiTenantApplicationId);
}
