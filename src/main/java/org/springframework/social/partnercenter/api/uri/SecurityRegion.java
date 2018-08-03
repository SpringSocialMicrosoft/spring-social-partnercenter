package org.springframework.social.partnercenter.api.uri;

import java.util.Optional;
import java.util.stream.Stream;

public enum SecurityRegion {
	USA("https://login.microsoftonline.com", "https://graph.windows.net", "https://api.partnercenter.microsoft.com"),
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

	public static Optional<SecurityRegion> forAuthority(String authority) {
		return Stream.of(values())
				.filter(region -> authority.equalsIgnoreCase(region.getAuthority()))
				.findFirst();
	}
}
