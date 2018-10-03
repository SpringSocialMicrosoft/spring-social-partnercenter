package org.springframework.social.partnercenter.api.consent;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consent {
    private String displayName;
    private String applicationId;
    private List<ApplicationGrant> applicationGrants;

    public Consent() {
    }

    public Consent(String displayName, String applicationId, List<ApplicationGrant> applicationGrants) {
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

    public List<ApplicationGrant> getApplicationGrants() {
        return applicationGrants;
    }

    public void setApplicationGrants(List<ApplicationGrant> applicationGrants) {
        this.applicationGrants = applicationGrants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consent consent = (Consent) o;
        return Objects.equals(displayName, consent.displayName) &&
                Objects.equals(applicationId, consent.applicationId) &&
                Objects.equals(applicationGrants, consent.applicationGrants);
    }

    @Override
    public int hashCode() {

        return Objects.hash(displayName, applicationId, applicationGrants);
    }
}
