package org.springframework.social.partnercenter.api.consent;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationGrants {
    private String enterpriseApplicationId;
    private String scope;

    public ApplicationGrants(String enterpriseApplicationId, String scope) {
        this.enterpriseApplicationId = enterpriseApplicationId;
        this.scope = scope;
    }

    public String getEnterpriseApplicationId() {
        return enterpriseApplicationId;
    }

    public void setEnterpriseApplicationId(String enterpriseApplicationId) {
        this.enterpriseApplicationId = enterpriseApplicationId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
