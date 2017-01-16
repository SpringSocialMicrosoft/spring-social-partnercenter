package org.springframework.social.partnercenter.connect;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.oauth2.PartnerCenterAuthorizationTemplate;

public class PartnerCenterServiceProvider  extends AbstractOAuth2ServiceProvider<PartnerCenter> {
	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;
	private final Collection<ClientHttpRequestInterceptor> interceptors;
	private RetryTemplate retryTemplate;

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		apiVersion = DEFAULT_API_VERSION;
		this.interceptors = new LinkedHashSet<>();
	}
	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		apiVersion = DEFAULT_API_VERSION;
		this.retryTemplate = retryTemplate;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
		this.retryTemplate = retryTemplate;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, Collection<ClientHttpRequestInterceptor> interceptors){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = DEFAULT_API_VERSION;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = DEFAULT_API_VERSION;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.interceptors = interceptors;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors, RetryTemplate retryTemplate){
		super(new PartnerCenterAuthorizationTemplate(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.retryTemplate = retryTemplate;
		this.interceptors = interceptors;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(retryTemplate, accessToken, apiVersion, interceptors);
	}
}
