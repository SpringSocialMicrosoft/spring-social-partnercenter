package org.springframework.social.partnercenter.security;

import java.util.UUID;

public class DelegatedAccessGrant {
    private String authorizationCode;
    private String state;
    private UUID tenantId;

    public DelegatedAccessGrant(String authorizationCode, String state, UUID tenantId) {
        this.authorizationCode = authorizationCode;
        this.state = state;
        this.tenantId = tenantId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }
}
