package org.springframework.social.partnercenter.api.consent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationGrant {
    private String enterpriseApplicationId;
    @JsonIgnore
    private List<String> scopes;

    public ApplicationGrant() {
    }

    public ApplicationGrant(String enterpriseApplicationId, List<String> scopes) {
        this.enterpriseApplicationId = enterpriseApplicationId;
        this.scopes = scopes;
    }

    public ApplicationGrant(String enterpriseApplicationId) {
        this.enterpriseApplicationId = enterpriseApplicationId;
    }

    public String getEnterpriseApplicationId() {
        return enterpriseApplicationId;
    }

    public void setEnterpriseApplicationId(String enterpriseApplicationId) {
        this.enterpriseApplicationId = enterpriseApplicationId;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @JsonProperty("scope")
    public String getScope() {
        return String.join(",", this.scopes);
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        if (scope.contains(",")) {
            this.scopes = Arrays.asList(StringUtils.split(scope, ","));
        } else {
            this.scopes = Collections.singletonList(scope);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationGrant that = (ApplicationGrant) o;
        return Objects.equals(enterpriseApplicationId, that.enterpriseApplicationId) &&
                scopes.equals(that.scopes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(enterpriseApplicationId, scopes);
    }
}
