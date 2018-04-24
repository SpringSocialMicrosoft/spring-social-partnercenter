package org.springframework.social.partnercenter.api.relationships;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

public enum PartnerRelationshipType {
    IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF("is_indirect_cloud_solution_provider_of"),
    IS_INDIRECT_RESELLER_OF("is_indirect_reseller_of");

    private String jsonValue;

    PartnerRelationshipType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String jsonValue() {
        return jsonValue;
    }

    public String urlEncodedValue() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, jsonValue);
    }
}
