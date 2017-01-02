package org.springframework.social.partnercenter.connect;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.oauth2.PartnerCenterOAuth2Template;

public class PartnerCenterConnectionFactory extends OAuth2ConnectionFactory<PartnerCenter> {

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String tenant){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, tenant), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String tenant, String apiVersion){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, tenant, apiVersion), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, tenant, apiVersion, interceptors), new PartnerCenterApiAdapter());
	}

	private PartnerCenterConnectionFactory(String providerId, OAuth2ServiceProvider<PartnerCenter> serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	public Connection<PartnerCenter> createConnection(){
		AccessGrant accessGrant = getPartnerCenterOAuth2Operations().exchangeForAccess();
		return createConnection(accessGrant);
	}

	private PartnerCenterOAuth2Template getPartnerCenterOAuth2Operations() {
		return (PartnerCenterOAuth2Template) getOAuthOperations();
	}
}
