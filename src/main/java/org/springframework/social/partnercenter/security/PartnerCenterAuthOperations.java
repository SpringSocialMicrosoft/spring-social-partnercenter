package org.springframework.social.partnercenter.security;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.MultiValueMap;

public interface PartnerCenterAuthOperations {

	/**
	 * Exchange the authorization code for an access grant.
	 * @return the access grant.
	 */
	AccessGrant exchangeForAccess();
	/**
	 * Exchange the authorization code for an access grant.
	 * @param authorizationCode the authorization code returned by the provider upon user authorization
	 * @param additionalParameters any additional parameters to be sent when exchanging the authorization code for an access grant. Should not be encoded.
	 * @return the access grant.
	 */
	AccessGrant exchangeForAccess(String authorizationCode, MultiValueMap<String, String> additionalParameters);

	/**
	 * Exchanges user credentials for an access grant using OAuth2's Resource Owner Credentials Grant (aka, "password" grant).
	 * @param username the user's username on the provider
	 * @param password the user's password on the provider
	 * @param additionalParameters any additional parameters to be sent when exchanging the credentials for an access grant. Should not be encoded.
	 * @return the access grant.
	 */
	AccessGrant exchangeCredentialsForAccess(String username, String password, MultiValueMap<String, String> additionalParameters);


	/**
	 * Refreshes a previous access grant.
	 * @param additionalParameters any additional parameters to be sent when refreshing a previous access grant. Should not be encoded.
	 * @return the access grant.
	 */
	AccessGrant refreshAccess(MultiValueMap<String, String> additionalParameters);
}
