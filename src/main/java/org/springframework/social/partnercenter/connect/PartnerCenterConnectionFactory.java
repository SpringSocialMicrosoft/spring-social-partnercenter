package org.springframework.social.partnercenter.connect;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.connect.admin.PartnerCenterAdminConnection;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

public class PartnerCenterConnectionFactory extends BasePartnerCenterConnectionFactory {

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, retryTemplate, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, RetryTemplate retryTemplate){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, retryTemplate), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, retryTemplate, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, interceptors, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, apiVersion, interceptors, retryTemplate, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, Collection<ClientHttpRequestInterceptor> interceptors, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, interceptors, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String applicationId, String applicationSecret, String clientId, String tenant, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(applicationId, applicationSecret, clientId, tenant, interceptors, retryTemplate, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	private PartnerCenterConnectionFactory(String providerId, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	public PartnerCenterConnection createConnection(){
		AccessGrant accessGrant = getAuthOperations().exchangeForAccess();
		return (PartnerCenterConnection) createConnection(accessGrant);
	}

	public PartnerCenterAdminConnection createConnection(String username, String password){
		AccessGrant accessGrant = getAuthOperations().exchangeCredentialsForAccess(username, password, new OAuth2Parameters());
		return new PartnerCenterAdminConnection(getProviderId(), extractProviderUserId(accessGrant), username, password, accessGrant.getAccessToken(),
				accessGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}

	@Override
	public Connection<PartnerCenter> createConnection(AccessGrant accessGrant) {
		return super.createConnection(accessGrant);
	}

	@Override
	public Connection<PartnerCenter> createConnection(ConnectionData data) {
		return super.createConnection(data);
	}
}
