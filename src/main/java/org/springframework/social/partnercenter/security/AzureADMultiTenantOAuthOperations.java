package org.springframework.social.partnercenter.security;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.util.MultiValueMap;

public interface AzureADMultiTenantOAuthOperations {
    String buildAuthorizeUrl(String redirectUri, String state);

    String buildAuthorizeUrl(OAuth2Parameters additionalParams);

    /**
     * Exchange the authorization code for a refresh token
     * @param authorizationCode the authorization code returned by the provider upon user authorization
     * @param partnerTenantId the tenantId of the tenant that is being authorized
     * @param redirectUri the authorization callback url; this value must match the redirectUri registered with the provider
     * @param additionalParameters any additional parameters to be sent when exchanging the authorization code for an access grant. Should not be encoded.
     * @return the access grant.
     */
    String exchangeForRefreshToken(String authorizationCode, String partnerTenantId, String redirectUri, MultiValueMap<String, String> additionalParameters);

    DelegatedAccessGrant extractDelegatedAccessGrant(String body);

    /**
     * Exchange the refresh token for an access token
     * @param refreshToken the refresh token returned by {@link #exchangeForRefreshToken}
     * @param partnerTenantId the tenantId of the tenant that is being authorized
     * @param resource the resource for the access grant
     * @return the access grant.
     */
    AccessGrant exchangeRefreshTokenForAccess(String refreshToken, String resource, String partnerTenantId);

    /**
     * adds request and response logging to restTemplate
     * @param logLevel level at which logging will be written
     */
    void enableSlf4j(LogLevel logLevel);

    boolean isSlf4jEnabled();
}
