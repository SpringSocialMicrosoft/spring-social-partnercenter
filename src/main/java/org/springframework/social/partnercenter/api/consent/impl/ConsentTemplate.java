package org.springframework.social.partnercenter.api.consent.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.consent.Consent;
import org.springframework.social.partnercenter.api.consent.ConsentOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class ConsentTemplate extends AbstractTemplate implements ConsentOperations {
    public static final String APPLICATIONCONSENTS = "applicationconsents";
    private final RestResource restResource;

    public ConsentTemplate(RestResource restResource, boolean isAuthorized) {
        super(isAuthorized);
        this.restResource = restResource;
    }

    @Override
    public ResponseEntity<Consent> getConsent(String customerTenantId, String multiTenantApplicationId) {
        return restResource.request()
                .pathSegment(customerTenantId, APPLICATIONCONSENTS, multiTenantApplicationId)
                .get(Consent.class);
    }

    @Override
    public ResponseEntity<Consent> createConsent(String customerTenantId, Consent consent) {
        return restResource.request()
                .pathSegment(customerTenantId, APPLICATIONCONSENTS)
                .post(consent, Consent.class);
    }

    @Override
    public ResponseEntity deleteConsent(String customerTenantId, String multiTenantApplicationId) {
        return restResource.request()
                .pathSegment(customerTenantId, APPLICATIONCONSENTS, multiTenantApplicationId)
                .delete();
    }

    @Override
    protected String getProviderId() {
        return PartnerCenter.PROVIDER_ID;
    }
}
