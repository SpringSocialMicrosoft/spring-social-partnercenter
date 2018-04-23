package org.springframework.social.partnercenter.api.relationships;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PartnerRelationshipType {
    IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF("isIndirectCloudSolutionProviderOf"),
    IS_INDIRECT_RESELLER_OF("isIndirectResellerOf");

    private String jsonValue;

    PartnerRelationshipType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String jsonValue() {
        return jsonValue;
    }
}
