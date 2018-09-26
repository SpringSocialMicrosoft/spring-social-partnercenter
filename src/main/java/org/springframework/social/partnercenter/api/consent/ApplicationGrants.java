package org.springframework.social.partnercenter.api.consent;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationGrants {
    private String enterpriseApplicationId;
    private String scope;
}
