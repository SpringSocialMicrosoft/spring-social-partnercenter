package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.uri.SecurityRegion;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

public class PartnerCenterApplicationConnectionFactory extends BasePartnerCenterConnectionFactory {
	/**
	 * Create a {@link PartnerCenterApplicationConnectionFactory}.
	 *
	 * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
	 */
	public PartnerCenterApplicationConnectionFactory(PartnerCenterServiceProvider serviceProvider) {
		super(PartnerCenter.PROVIDER_ID, serviceProvider, new PartnerCenterApiAdapter());
	}

	public PartnerCenterApplicationConnectionFactory(String clientId, String clientSecret, String domain, SecurityRegion region){
		super(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(clientId, clientSecret, domain, region), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnection createConnection(){
		AccessGrant accessGrant = getAuthOperations().exchangeForAccess();
		return (PartnerCenterConnection) createConnection(accessGrant);
	}

	/**
	 * Create a OAuth2-based {@link Connection} from the {@link AccessGrant} returned after {@link #getAuthOperations() completing the OAuth2 flow}.
	 * @param accessGrant the access grant
	 * @return the new service provider connection
	 * @see OAuth2Operations#exchangeForAccess(String, String, org.springframework.util.MultiValueMap)
	 */
	public Connection<PartnerCenter> createConnection(AccessGrant accessGrant) {
		return new PartnerCenterConnection(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}

	public boolean canConnect(){
		try {
			createConnection().getApi().getCustomerOperations().getList(1);
			return true;
		} catch (Exception e){
			return false;
		}
	}
}
