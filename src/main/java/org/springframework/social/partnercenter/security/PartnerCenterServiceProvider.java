package org.springframework.social.partnercenter.security;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterAdminTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;

public class PartnerCenterServiceProvider extends AbstractAzureADServiceProvider<PartnerCenter>{

	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;
	private final Collection<ClientHttpRequestInterceptor> interceptors;
	private RetryTemplate retryTemplate;
	private final UriProvider uriProvider;

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, UriProvider.US));
		apiVersion = DEFAULT_API_VERSION;
		this.uriProvider = UriProvider.US;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.retryTemplate = retryTemplate;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, RetryTemplate retryTemplate){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.US;
		this.retryTemplate = retryTemplate;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
		this.retryTemplate = retryTemplate;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, Collection<ClientHttpRequestInterceptor> interceptors, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = DEFAULT_API_VERSION;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = DEFAULT_API_VERSION;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(applicationId, applicationSecret, clientId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(retryTemplate, uriProvider, accessToken, apiVersion, interceptors);
	}

	public PartnerCenterAdmin getAdminApi(String accessToken) {
		return new PartnerCenterAdminTemplate(retryTemplate, uriProvider, accessToken, apiVersion, interceptors);
	}
}
