package org.springframework.social.partnercenter.security;

import org.springframework.social.oauth2.OAuth2ServiceProvider;

public abstract class AbstractAzureADServiceProvider<T> implements AzureADServiceProvider<T> {

	private final AzureADAuthOperations azureADAuthOperations;

	/**
	 * Create a new {@link OAuth2ServiceProvider}.
	 * @param azureADAuthOperations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
	 */
	public AbstractAzureADServiceProvider(AzureADAuthOperations azureADAuthOperations) {
		this.azureADAuthOperations = azureADAuthOperations;
	}

	// implementing OAuth2ServiceProvider

	public AzureADAuthOperations getAzureADAuthOperations() {
		return azureADAuthOperations;
	}

	public abstract T getApi(String accessToken);
}
