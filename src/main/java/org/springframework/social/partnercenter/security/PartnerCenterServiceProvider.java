package org.springframework.social.partnercenter.security;

import static org.springframework.social.partnercenter.api.uri.UriProvider.DEFAULT_URL_PROVIDER;
import static org.springframework.social.partnercenter.api.uri.UriProvider.fromSecurityRegion;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterAdminTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.api.uri.SecurityRegion;
import org.springframework.social.partnercenter.api.uri.UriProvider;

public class PartnerCenterServiceProvider extends AbstractAzureADServiceProvider<PartnerCenter>{

	private final static String DEFAULT_API_VERSION = "v1";
	private final String apiVersion;
	private final UriProvider uriProvider;

	public PartnerCenterServiceProvider(String clientId, String clientSecret, String domain){
		super(new AzureADAuthTemplate(clientId, clientSecret, null, domain, DEFAULT_URL_PROVIDER));
		apiVersion = DEFAULT_API_VERSION;
		this.uriProvider = DEFAULT_URL_PROVIDER;
	}

	public PartnerCenterServiceProvider(String clientId, String clientSecret, String domain, SecurityRegion securityRegion){
		super(new AzureADAuthTemplate(clientId, clientSecret, null, domain, UriProvider.fromSecurityRegion(securityRegion)));
		apiVersion = DEFAULT_API_VERSION;
		this.uriProvider = UriProvider.fromSecurityRegion(securityRegion);
	}

	@Deprecated
	public PartnerCenterServiceProvider(String clientId, String clientSecret, String nativeAppId, String domain, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, nativeAppId, domain, authority, resourceUrl, partnerServiceApiRoot));
		apiVersion = DEFAULT_API_VERSION;
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
	}

	public PartnerCenterServiceProvider(String clientId, String clientSecret, String domain, String apiVersion, SecurityRegion region){
		super(new AzureADAuthTemplate(clientId, clientSecret, null, domain, fromSecurityRegion(region)));
		uriProvider = UriProvider.fromSecurityRegion(region);
		this.apiVersion = apiVersion;
	}

	@Deprecated
	public PartnerCenterServiceProvider(String clientId, String clientSecret, String nativeAppId, String domain, String apiVersion, String authority, String resourceUrl, String partnerServiceApiRoot){
		super(new AzureADAuthTemplate(clientId, clientSecret, nativeAppId, domain, authority, resourceUrl, partnerServiceApiRoot));
		uriProvider = UriProvider.builder().authority(authority).partnerServiceApiRoot(partnerServiceApiRoot).resourceUrl(resourceUrl).build();
		this.apiVersion = apiVersion;
	}

	@Deprecated
	public PartnerCenterServiceProvider(String clientId, String clientSecret, String nativeAppId, String domain, String apiVersion, SecurityRegion region){
		super(new AzureADAuthTemplate(clientId, clientSecret, nativeAppId, domain, region.getAuthority(), region.getResourceUrl(), region.getPartnerServiceApiRoot()));
		uriProvider = UriProvider.fromSecurityRegion(region);
		this.apiVersion = apiVersion;
	}

	@Override
	public PartnerCenter getApi(String accessToken) {
		return new PartnerCenterTemplate(uriProvider, accessToken, apiVersion);
	}

	public PartnerCenterAdmin getAdminApi(String accessToken) {
		return new PartnerCenterAdminTemplate(uriProvider, accessToken, apiVersion);
	}

	/**
	 * Builds a service provider intended for Application only authentication
	 * @param clientId Web App App ID for the partner center application
	 * @param clientSecret The App Key generated in the Partner Center portal for the client application
	 * @param domain domain associated with the CSP Provider account
	 * @param securityRegion The cloud instance of Partner Center. Either DE for cloud germany or US for global
	 * @return
	 */
	public static PartnerCenterServiceProvider withApplicationCredentials(String clientId, String clientSecret, String domain, SecurityRegion securityRegion) {
		return new PartnerCenterServiceProvider(clientId, clientSecret, domain, securityRegion);
	}

	/**
	 * Builds a service provider intended for App + User authentication
	 * @param clientId Native App App ID configured in partner center
	 * @param domain domain associated with the CSP Provider account
	 * @param securityRegion The cloud instance of Partner Center. Either DE for cloud germany or US for global
	 * @return
	 */
	public static PartnerCenterServiceProvider withUserCredentials(String clientId, String domain, SecurityRegion securityRegion) {
		return new PartnerCenterServiceProvider(clientId, null, domain, securityRegion);
	}
}
