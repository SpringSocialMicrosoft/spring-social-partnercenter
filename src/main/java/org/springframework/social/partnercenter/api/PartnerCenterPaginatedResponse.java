package org.springframework.social.partnercenter.api;

import java.util.Map;

public class PartnerCenterPaginatedResponse<T> extends PartnerCenterResponse<T> {
	private Map<String, String> links;

	public Map<String, String> getLinks() {
		return links;
	}

	public PartnerCenterPaginatedResponse setLinks(Map<String, String> links) {
		this.links = links;
		return this;
	}
}
