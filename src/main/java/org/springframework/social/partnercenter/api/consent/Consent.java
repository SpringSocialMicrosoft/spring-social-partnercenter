package org.springframework.social.partnercenter.api.consent;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consent {
    private String displayName;
    private String applicationId;
    private ApplicationGrants applicationGrants;
}
