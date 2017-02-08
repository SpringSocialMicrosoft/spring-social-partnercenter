package org.springframework.social.partnercenter.api.uri;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {
	public static String AUTHORITY = "https://login.microsoftonline.de";
	public static String RESOURCE_URL = "https://api.partnercenter.microsoft.com";
	public static String PARTNER_SERVICE_API_ROOT = "https://api.partnercenter.microsoft.com";

	public static String buildPartnerCenterOAuth2Uri(String tenantId){
		return UriComponentsBuilder.fromUriString(AUTHORITY)
				.pathSegment(tenantId)
				.pathSegment("oauth2")
				.pathSegment("token")
				.build()
				.toString();
	}

	public static String buildPartnerCenterTokenUri(){
		return UriComponentsBuilder.fromUriString(PARTNER_SERVICE_API_ROOT)
				.pathSegment("generatetoken")
				.build().toString();
	}

	public static UriComponentsBuilder partnerCenterBuilder(){
		return UriComponentsBuilder.fromUriString(PARTNER_SERVICE_API_ROOT);
	}

	public static UriComponentsBuilder partnerCenterInvoiceUri(){
		return partnerCenterBuilder().pathSegment("v1", "invoices");
	}

	public static UriComponentsBuilder partnerCenterPricingUri(){
		return partnerCenterBuilder().pathSegment("v1", "ratecards", "azure");
	}

	public static UriComponentsBuilder partnerCenterOfferUri(){
		return partnerCenterBuilder().pathSegment("v1", "offers");
	}

	public static UriComponentsBuilder partnerCenterCustomerUri(){
		return partnerCenterBuilder().pathSegment("v1", "customers");
	}

	public static UriComponentsBuilder partnerCenterProfileUri(){
		return partnerCenterBuilder().pathSegment("v1", "profiles");
	}
}
