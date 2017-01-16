package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.partnercenter.PartnerCenter;

public class BasePartnerCenterConnectionFactory extends ConnectionFactory<PartnerCenter> {

	/**
	 * Create a {@link OAuth2ConnectionFactory}.
	 * @param providerId the provider id e.g. "facebook"
	 * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
	 * @param apiAdapter the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
	 */
	public BasePartnerCenterConnectionFactory(String providerId, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	/**
	 * Get the ServiceProvider's {@link OAuth2Operations} that allows the client application to conduct the OAuth2 flow with the provider.
	 * @return an OAuth2Operations
	 */
	public OAuth2Operations getOAuthOperations() {
		return getPartnerCenterServiceProvider().getOAuthOperations();
	}

	/**
	 * Create a OAuth2-based {@link Connection} from the {@link AccessGrant} returned after {@link #getOAuthOperations() completing the OAuth2 flow}.
	 * @param accessGrant the access grant
	 * @return the new service provider connection
	 * @see OAuth2Operations#exchangeForAccess(String, String, org.springframework.util.MultiValueMap)
	 */
	public Connection<PartnerCenter> createConnection(AccessGrant accessGrant) {
		return new PartnerCenterConnection(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}

	/**
	 * Create a OAuth2-based {@link Connection} from the connection data.
	 * @param data connection data from which to create the connection
	 */
	public Connection<PartnerCenter> createConnection(ConnectionData data) {
		return new PartnerCenterConnection(data, getPartnerCenterServiceProvider(), getApiAdapter());
	}

	// subclassing hooks

	/**
	 * Hook for extracting the providerUserId from the returned {@link AccessGrant}, if it is available.
	 * Default implementation returns null, indicating it is not exposed and another remote API call will be required to obtain it.
	 * Subclasses may override.
	 * @param accessGrant an AccessGrant from which to extract the provider ID
	 * @return the pvodier ID, if available
	 */
	protected String extractProviderUserId(AccessGrant accessGrant) {
		return null;
	}

	// internal helpers

	private PartnerCenterServiceProvider getPartnerCenterServiceProvider() {
		return (PartnerCenterServiceProvider) getServiceProvider();
	}

}
