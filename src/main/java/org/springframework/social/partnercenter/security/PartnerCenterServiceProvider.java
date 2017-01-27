package org.springframework.social.partnercenter.security;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.api.admin.PartnerCenterAdminTemplate;

public class PartnerCenterServiceProvider extends AbstractAzureADServiceProvider<PartnerCenter>{

	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;
	private final Collection<ClientHttpRequestInterceptor> interceptors;
	private RetryTemplate retryTemplate;

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		apiVersion = DEFAULT_API_VERSION;
		this.interceptors = new LinkedHashSet<>();
	}
	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		apiVersion = DEFAULT_API_VERSION;
		this.retryTemplate = retryTemplate;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
		this.retryTemplate = retryTemplate;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, Collection<ClientHttpRequestInterceptor> interceptors){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = DEFAULT_API_VERSION;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = DEFAULT_API_VERSION;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = apiVersion;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String clientId, String domain, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthTemplate(applicationId, applicationSecret, clientId, domain));
		this.apiVersion = apiVersion;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(retryTemplate, accessToken, apiVersion, interceptors);
	}

	public PartnerCenterAdmin getAdminApi(String accessToken) {
		return new PartnerCenterAdminTemplate(retryTemplate, accessToken, apiVersion, interceptors);
	}
}
