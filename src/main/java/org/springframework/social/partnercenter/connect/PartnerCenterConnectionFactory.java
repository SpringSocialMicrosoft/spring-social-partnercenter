package org.springframework.social.partnercenter.connect;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.oauth2.PartnerCenterAuthorizationTemplate;

public class PartnerCenterConnectionFactory extends BasePartnerCenterConnectionFactory {

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant), new PartnerCenterApiAdapter());
	}
	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, RetryTemplate retryTemplate){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, retryTemplate), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, RetryTemplate retryTemplate){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, retryTemplate), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, interceptors), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, interceptors, retryTemplate), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, Collection<ClientHttpRequestInterceptor> interceptors){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, interceptors), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, interceptors, retryTemplate), new PartnerCenterApiAdapter());
	}

	private PartnerCenterConnectionFactory(String providerId, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	public PartnerCenterConnection createConnection(){
		AccessGrant accessGrant = getPartnerCenterOAuth2Operations().exchangeForAccess();
		return (PartnerCenterConnection) createConnection(accessGrant);
	}

	@Override
	public Connection<PartnerCenter> createConnection(AccessGrant accessGrant) {
		return super.createConnection(accessGrant);
	}

	@Override
	public Connection<PartnerCenter> createConnection(ConnectionData data) {
		return super.createConnection(data);
	}

	private PartnerCenterAuthorizationTemplate getPartnerCenterOAuth2Operations() {
		return (PartnerCenterAuthorizationTemplate) getOAuthOperations();
	}
}
