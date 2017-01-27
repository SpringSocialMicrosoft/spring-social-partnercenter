package org.springframework.social.partnercenter.security;

import org.springframework.social.ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

public interface AzureADServiceProvider<T> extends ServiceProvider<T>{
	/**
	 * Get the service interface for carrying out the "OAuth dance" with this provider.
	 * The result of the OAuth dance is an access token that can be used to obtain a {@link #getApi(String) API binding}.
	 * @return an {@link OAuth2Operations} for carrying out the "OAuth dance" with this provider.
	 */
	PartnerCenterAuthOperations getPartnerCenterAuthOperations();

	/**
	 * Returns an API interface allowing the client application to access protected resources on behalf of a user.
	 * @param accessToken the API access token
	 * @return a binding to the service provider's API
	 */
	T getApi(String accessToken);
}
