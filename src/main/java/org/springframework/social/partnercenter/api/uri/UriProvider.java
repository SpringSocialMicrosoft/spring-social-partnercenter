package org.springframework.social.partnercenter.api.uri;

import static org.springframework.social.partnercenter.api.uri.SecurityRegion.DEU;
import static org.springframework.social.partnercenter.api.uri.SecurityRegion.USA;

import org.springframework.web.util.UriComponentsBuilder;

public class UriProvider {
	public static final UriProvider US = fromSecurityRegion(USA);
	public static final UriProvider DE = fromSecurityRegion(DEU);
	public static final UriProvider DEFAULT_URL_PROVIDER = UriProvider.US;

	private String authority;
	private String resourceUrl;
	private String partnerServiceApiRoot;
	private String domain;

	private UriProvider(String authority, String resourceUrl, String partnerServiceApiRoot) {
		this.authority = authority;
		this.resourceUrl = resourceUrl;
		this.partnerServiceApiRoot = partnerServiceApiRoot;
	}

	private UriProvider() {}

	public String buildPartnerCenterOAuth2Uri(String tenantId){
		this.domain = tenantId;
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

	public String getAuthority(){
		return authority;
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

	public UriComponentsBuilder partnerCenterRelationshipsUri() {
		return partnerCenterBuilder().pathSegment("v1", "relationships");
	}

	public  UriComponentsBuilder partnerBaseUri(){
		return partnerCenterBuilder().pathSegment("v1");
	}

	public  UriComponentsBuilder auditUri(){
		return partnerCenterBuilder().pathSegment("v1/auditrecords");
	}

	public  UriComponentsBuilder partnerCenterProfileUri(){
		return partnerCenterBuilder().pathSegment("v1", "profiles");
	}

	public static UriProviderBuilder builder(){
		return new UriProviderBuilder();
	}

	public static UriProvider fromSecurityRegion(SecurityRegion region){
		return UriProvider.builder()
				.authority(region.getAuthority())
				.resourceUrl(region.getResourceUrl())
				.partnerServiceApiRoot(region.getPartnerServiceApiRoot())
				.build();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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
