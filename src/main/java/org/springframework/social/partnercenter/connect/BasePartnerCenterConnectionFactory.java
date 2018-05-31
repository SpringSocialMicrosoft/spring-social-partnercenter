package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.connect.admin.PartnerCenterAdminConnection;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.security.AzureADAuthOperations;
import org.springframework.social.partnercenter.security.PartnerCenterAccessGrant;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

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
	 * Get the ServiceProvider's {@link AzureADAuthOperations} that allows the client application to conduct the OAuth2 flow with the provider.
	 * @return an OAuth2Operations
	 */
	public AzureADAuthOperations getAuthOperations() {
		return getPartnerCenterServiceProvider().getAzureADAuthOperations();
	}

	/**
	 * Create a OAuth2-based {@link Connection} from the connection data.
	 * @param data connection data from which to create the connection
	 */
	public Connection<PartnerCenter> createConnection(ConnectionData data) {
		return new PartnerCenterAdminConnection(data, getApiAdapter(), getPartnerCenterServiceProvider());
	}

	// subclassing hooks

	/**
	 * Hook for extracting the providerUserId from the returned {@link AccessGrant}, if it is available.
	 * Default implementation returns null, indicating it is not exposed and another remote API call will be required to obtain it.
	 * Subclasses may override.
	 * @param accessGrant an AccessGrant from which to extract the provider ID
	 * @return the providerUser ID, if available
	 */
	String extractProviderUserId(AccessGrant accessGrant) {
		PartnerCenterAccessGrant grant = (PartnerCenterAccessGrant) accessGrant;

		try {
			return PartnerCenterJWT.fromTokenString(grant.getIdToken()).getOid().toString();
		} catch (Exception e) {
			return null;
		}
	}

	public void enableSl4fjForAuthRequests(LogLevel logLevel){
		getAuthOperations().enableSlf4j(logLevel);
	}

	// internal helpers

	PartnerCenterServiceProvider getPartnerCenterServiceProvider() {
		return (PartnerCenterServiceProvider) getServiceProvider();
	}

}
