package org.springframework.social.partnercenter.connect;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.oauth2.PartnerCenterOAuth2Template;

public class PartnerCenterServiceProvider  extends AbstractOAuth2ServiceProvider<PartnerCenter> {
	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;
	private final Collection<ClientHttpRequestInterceptor> interceptors;

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant){
		super(new PartnerCenterOAuth2Template(applicationId, applicationSecret, tenant));
		apiVersion = DEFAULT_API_VERSION;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion){
		super(new PartnerCenterOAuth2Template(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.interceptors = new LinkedHashSet<>();
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion, Collection<ClientHttpRequestInterceptor> interceptors){
		super(new PartnerCenterOAuth2Template(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
		this.interceptors = interceptors;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(accessToken, apiVersion, interceptors);
	}
}
