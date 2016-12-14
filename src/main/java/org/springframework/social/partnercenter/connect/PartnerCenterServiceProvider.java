package org.springframework.social.partnercenter.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.impl.PartnerCenterTemplate;
import org.springframework.social.partnercenter.oauth2.PartnerCenterOAuth2Template;

public class PartnerCenterServiceProvider  extends AbstractOAuth2ServiceProvider<PartnerCenter> {
	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant){
		super(new PartnerCenterOAuth2Template(applicationId, applicationSecret, tenant));
		apiVersion = DEFAULT_API_VERSION;
	}

	public PartnerCenterServiceProvider(String applicationId, String applicationSecret, String tenant, String apiVersion){
		super(new PartnerCenterOAuth2Template(applicationId, applicationSecret, tenant));
		this.apiVersion = apiVersion;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(accessToken, apiVersion);
	}
}
