package org.springframework.social.partnercenter.connect;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.uri.SecurityRegion;
import org.springframework.social.partnercenter.connect.admin.PartnerCenterAdminConnection;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

public class PartnerCenterUserConnectionFactory extends BasePartnerCenterConnectionFactory {
	/**
	 * Create a {@link PartnerCenterUserConnectionFactory}.
	 *
	 * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
	 */
	public PartnerCenterUserConnectionFactory(PartnerCenterServiceProvider serviceProvider) {
		super(PartnerCenter.PROVIDER_ID, serviceProvider, new PartnerCenterApiAdapter());
	}

	public PartnerCenterUserConnectionFactory(String clientId, String domain, SecurityRegion region){
		super(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(null, null, clientId, domain, "v1", region), new PartnerCenterApiAdapter());
	}

	public PartnerCenterAdminConnection createConnection(String username, String password){
		AccessGrant windowsLoginAccessGrant = getAuthOperations().exchangeCredentialsForAccess(username, password, new OAuth2Parameters());
		final AccessGrant partnerCenterGrant = getAuthOperations().exchangeForAccess(windowsLoginAccessGrant.getAccessToken(), null);

		return new PartnerCenterAdminConnection(getProviderId(), extractProviderUserId(windowsLoginAccessGrant), windowsLoginAccessGrant.getRefreshToken(), partnerCenterGrant.getAccessToken(),
				partnerCenterGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}

	public boolean canConnect(String username, String password){
		try {
			createConnection(username, password).getApi().getCustomerOperations().getList(1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
