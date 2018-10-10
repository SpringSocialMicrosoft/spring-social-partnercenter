package org.springframework.social.partnercenter.connect;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.uri.SecurityRegion;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.security.AzureADMultiTenantOAuthOperations;
import org.springframework.social.partnercenter.security.AzureADMultiTenantOAuthTemplate;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

public class PartnerCenterMultiTenantConnectionFactory extends BasePartnerCenterConnectionFactory {
    private final AzureADMultiTenantOAuthOperations multiTenantOAuthOperations;
    private final String clientId;
    private final String clientSecret;

    private final SecurityRegion securityRegion;

    public PartnerCenterMultiTenantConnectionFactory(String clientId, String clientSecret, SecurityRegion securityRegion) {
        super(PartnerCenter.PROVIDER_ID, null, new PartnerCenterApiAdapter());
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.securityRegion = securityRegion;
        this.multiTenantOAuthOperations = new AzureADMultiTenantOAuthTemplate(clientId, clientSecret, UriProvider.fromSecurityRegion(securityRegion));
    }

    /**
     * Create a connection to Partner Center
     *
     * @param refreshToken    obtained from {@link AzureADMultiTenantOAuthOperations#exchangeForRefreshToken}
     * @param partnerTenantId tenantId of the csp account for which the connection is created
     * @param domain          domain of the csp account
     * @return the PartnerCenterConnection that allows operations with Partner Center APIs
     */
    public PartnerCenterConnection createConnection(String refreshToken, String partnerTenantId, String domain) {
        AccessGrant accessGrant = multiTenantOAuthOperations
                .exchangeRefreshTokenForAccess(refreshToken, securityRegion.getPartnerServiceApiRoot(), partnerTenantId);

        return new PartnerCenterConnection(getProviderId(),
                extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(),
                accessGrant.getExpireTime(),
                new PartnerCenterServiceProvider(clientId, clientSecret, domain, securityRegion),
                getApiAdapter());
    }

    public boolean canConnect(String refreshToken, String partnerTenantId, String domain) {
        try {
            createConnection(refreshToken, partnerTenantId, domain).getApi().getCustomerOperations().getList(1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AzureADMultiTenantOAuthOperations getMultiTenantOAuthOperations() {
        return multiTenantOAuthOperations;
    }

    @Override
    public void enableSl4fjForAuthRequests(LogLevel logLevel) {
        super.enableSl4fjForAuthRequests(logLevel);
        multiTenantOAuthOperations.enableSlf4j(logLevel);
    }

    public SecurityRegion getSecurityRegion() {
        return securityRegion;
    }
}
