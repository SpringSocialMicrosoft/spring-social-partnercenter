package org.springframework.social.partnercenter.api.uri;

public enum SecurityRegion {
	USA("https://login.windows.net", "https://graph.windows.net", "https://api.partnercenter.microsoft.com"),
	DEU("https://login.microsoftonline.de", "https://graph.cloudapi.de", "https://api.partnercenter.microsoft.com");

	private String authority;
	private String resourceUrl;
	private String partnerServiceApiRoot;

	SecurityRegion(String authority, String resourceUrl, String partnerServiceApiRoot) {
		this.authority = authority;
		this.resourceUrl = resourceUrl;
		this.partnerServiceApiRoot = partnerServiceApiRoot;
	}

	public String getAuthority() {
		return authority;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public String getPartnerServiceApiRoot() {
		return partnerServiceApiRoot;
	}
}
