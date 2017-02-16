package org.springframework.social.partnercenter.api.uri;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {
	public static final UriProvider US = builder()
			.authority("https://login.windows.net")
			.partnerServiceApiRoot("https://api.partnercenter.microsoft.com")
			.resourceUrl("https://graph.windows.net")
			.build();
	public static final UriProvider DE = builder()
			.authority("https://login.microsoftonline.de")
			.partnerServiceApiRoot("https://api.partnercenter.microsoft.com")
			.resourceUrl("https://graph.cloudapi.de")
			.build();
	public static final UriProvider DEFAULT_URL_PROVIDER = UriProvider.DE;

	private String authority;
	private String resourceUrl;
	private String partnerServiceApiRoot;

	private UriProvider(String authority, String resourceUrl, String partnerServiceApiRoot) {
		this.authority = authority;
		this.resourceUrl = resourceUrl;
		this.partnerServiceApiRoot = partnerServiceApiRoot;
	}

	private UriProvider() {}

	public String buildPartnerCenterOAuth2Uri(String tenantId){
		return UriComponentsBuilder.fromUriString(authority)
				.pathSegment(tenantId)
				.pathSegment("oauth2")
				.pathSegment("token")
				.build()
				.toString();
	}

	public  String getResourceUri(){
		return resourceUrl;
	}

	public  String getPartnerServiceApiRoot(){
		return partnerServiceApiRoot;
	}

	public  String buildPartnerCenterTokenUri(){
		return UriComponentsBuilder.fromUriString(partnerServiceApiRoot)
				.pathSegment("generatetoken")
				.build().toString();
	}

	public  UriComponentsBuilder partnerCenterBuilder(){
		return UriComponentsBuilder.fromUriString(partnerServiceApiRoot);
	}

	public  UriComponentsBuilder partnerCenterInvoiceUri(){
		return partnerCenterBuilder().pathSegment("v1", "invoices");
	}

	public  UriComponentsBuilder partnerCenterPricingUri(){
		return partnerCenterBuilder().pathSegment("v1", "ratecards", "azure");
	}

	public  UriComponentsBuilder partnerCenterOfferUri(){
		return partnerCenterBuilder().pathSegment("v1", "offers");
	}

	public  UriComponentsBuilder partnerCenterCustomerUri(){
		return partnerCenterBuilder().pathSegment("v1", "customers");
	}

	public  UriComponentsBuilder partnerCenterProfileUri(){
		return partnerCenterBuilder().pathSegment("v1", "profiles");
	}

	public static UriProviderBuilder builder(){
		return new UriProviderBuilder();
	}

	public static class UriProviderBuilder{
		private String authority;
		private String resourceUrl;
		private String partnerServiceApiRoot;

		public UriProviderBuilder authority(String authority) {
			this.authority = authority;
			return this;
		}

		public UriProviderBuilder resourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
			return this;
		}

		public UriProviderBuilder partnerServiceApiRoot(String partnerServiceApiRoot) {
			this.partnerServiceApiRoot = partnerServiceApiRoot;
			return this;
		}

		public UriProvider build(){
			return new UriProvider(authority, resourceUrl, partnerServiceApiRoot);
		}
	}
}
