package org.springframework.social.partnercenter.api.consent;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consent {
    private String displayName;
    private String applicationId;
    private ApplicationGrants applicationGrants;

    public Consent(String displayName, String applicationId, ApplicationGrants applicationGrants) {
        this.displayName = displayName;
        this.applicationId = applicationId;
        this.applicationGrants = applicationGrants;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ApplicationGrants getApplicationGrants() {
        return applicationGrants;
    }

    public void setApplicationGrants(ApplicationGrants applicationGrants) {
        this.applicationGrants = applicationGrants;
    }
}
