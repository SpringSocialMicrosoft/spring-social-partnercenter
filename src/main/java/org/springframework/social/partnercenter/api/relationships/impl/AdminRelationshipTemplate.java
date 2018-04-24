package org.springframework.social.partnercenter.api.relationships.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.relationships.AdminRelationshipOperations;
import org.springframework.social.partnercenter.api.relationships.PartnerRelationship;
import org.springframework.social.partnercenter.api.relationships.PartnerRelationshipType;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.serialization.Json;

public class AdminRelationshipTemplate extends PagingResourceTemplate<PartnerRelationship> implements AdminRelationshipOperations {
    public static final String RELATIONSHIP_TYPE = "relationship_type";
    private static final String CUSTOMER = "customer";
    private final RestResource restResource;

    public AdminRelationshipTemplate(RestResource restResource, boolean isAuthorized) {
        super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<PartnerRelationship>>() {
        });
        this.restResource = restResource;
    }

    @Override
    public ResponseEntity<PartnerCenterResponse<PartnerRelationship>> getPartnerRelationships(PartnerRelationshipType relationshipType) {
        return restResource.request()
                .queryParam(RELATIONSHIP_TYPE, relationshipType.urlEncodedValue())
                .get(new ParameterizedTypeReference<PartnerCenterResponse<PartnerRelationship>>() {});
    }

    @Override
    public ResponseEntity<PartnerCenterResponse<PartnerRelationship>> getPartnerRelationships(PartnerRelationshipType relationshipType, String partnerId) {
        return restResource.request()
                .pathSegment(partnerId, CUSTOMER)
                .queryParam(RELATIONSHIP_TYPE, relationshipType.urlEncodedValue())
                .get(new ParameterizedTypeReference<PartnerCenterResponse<PartnerRelationship>>() {});
    }

    @Override
    public ResponseEntity<Void> retryFailedRelationship(String customerId, String partnerId) {
        return restResource.request()
                .pathSegment(partnerId, CUSTOMER, customerId)
                .patch(null, Void.class);
    }

    @Override
    protected String getProviderId() {
        return PartnerCenter.PROVIDER_ID;
    }
}
