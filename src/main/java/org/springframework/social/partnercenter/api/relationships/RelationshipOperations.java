package org.springframework.social.partnercenter.api.relationships;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface RelationshipOperations {
    ResponseEntity<PartnerCenterResponse<PartnerRelationship>> getPartnerRelationships(PartnerRelationshipType relationshipType);

    ResponseEntity<PartnerCenterResponse<PartnerRelationship>> getPartnerRelationships(PartnerRelationshipType relationshipType, String partnerId);

    ResponseEntity<Void> retryFailedRelationship(String customerId, String partnerId);
}
