package org.springframework.social.partnercenter.api.uri;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {
	public static String OAUTH_HOST = "https://login.windows.net";
	public static String GRAPH_URL = "https://graph.windows.net";
	public static String PARTNER_CENTER_URL = "https://api.partnercenter.microsoft.com";

	public static String buildPartnerCenterOAuth2Uri(String tenantId){
		return UriComponentsBuilder.fromUriString(OAUTH_HOST)
				.pathSegment(tenantId)
				.pathSegment("oauth2")
				.pathSegment("token")
				.build()
				.toString();
	}

	public static String buildPartnerCenterTokenUri(){
		return UriComponentsBuilder.fromUriString(PARTNER_CENTER_URL)
				.pathSegment("generatetoken")
				.build().toString();
	}

	public static UriComponentsBuilder partnerCenterBuilder(){
		return UriComponentsBuilder.fromUriString(PARTNER_CENTER_URL);
	}

	public static UriComponentsBuilder partnerCenterCustomerApiBuilder(){
		return UriComponentsBuilder.fromUriString(PARTNER_CENTER_URL).pathSegment("v1", "customers");
	}
}
