package org.springframework.social.partnercenter.connect;

import java.util.Optional;

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

    public PartnerCenterMultiTenantConnectionFactory(String clientId, String clientSecret){
        super(PartnerCenter.PROVIDER_ID, null, new PartnerCenterApiAdapter());
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.multiTenantOAuthOperations = new AzureADMultiTenantOAuthTemplate(clientId, clientSecret, UriProvider.DEFAULT_URL_PROVIDER);
    }

    /**
     * Create a connection to Partner Center
     * @param refreshToken obtained from {@link AzureADMultiTenantOAuthOperations#exchangeForRefresh}
     * @param partnerTenantId tenantId of the csp account for which the connection is created
     * @param domain domain of the csp account
     * @param securityRegion security region of the csp account if null will default to US
     */
    public PartnerCenterConnection createConnection(String refreshToken, String partnerTenantId, String domain, SecurityRegion securityRegion) {
        AccessGrant accessGrant = multiTenantOAuthOperations
                .exchangeForAccess(refreshToken, securityRegion.getPartnerServiceApiRoot(), partnerTenantId);

        return new PartnerCenterConnection(getProviderId(),
                extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(),
                accessGrant.getExpireTime(),
                new PartnerCenterServiceProvider(clientId, clientSecret, domain, Optional.ofNullable(securityRegion)
                        .orElse(SecurityRegion.USA)),
                getApiAdapter());
    }

    public boolean canConnect(String refreshToken, String partnerTenantId, String domain, SecurityRegion securityRegion) {
        try {
            createConnection(refreshToken, partnerTenantId, domain, securityRegion).getApi().getCustomerOperations().getList(1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AzureADMultiTenantOAuthOperations getMultiTenantOAuthOperations() {
        return multiTenantOAuthOperations;
    }

    @Override
    public void enableSl4fjForAuthRequests(LogLevel logLevel){
        super.enableSl4fjForAuthRequests(logLevel);
        multiTenantOAuthOperations.enableSlf4j(logLevel);
    }
}
