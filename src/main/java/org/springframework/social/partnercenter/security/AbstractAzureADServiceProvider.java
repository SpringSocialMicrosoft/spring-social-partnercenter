package org.springframework.social.partnercenter.security;

import org.springframework.social.oauth2.OAuth2ServiceProvider;

public abstract class AbstractAzureADServiceProvider<T> implements AzureADServiceProvider<T> {

	private final PartnerCenterAuthOperations partnerCenterAuthOperations;

	/**
	 * Create a new {@link OAuth2ServiceProvider}.
	 * @param partnerCenterAuthOperations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
	 */
	public AbstractAzureADServiceProvider(PartnerCenterAuthOperations partnerCenterAuthOperations) {
		this.partnerCenterAuthOperations = partnerCenterAuthOperations;
	}

	// implementing OAuth2ServiceProvider

	public PartnerCenterAuthOperations getPartnerCenterAuthOperations() {
		return partnerCenterAuthOperations;
	}

	public abstract T getApi(String accessToken);
}
