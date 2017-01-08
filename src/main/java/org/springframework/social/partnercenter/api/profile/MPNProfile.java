package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MPNProfile {
	private String mpnId;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getMpnId() {
		return mpnId;
	}

	public MPNProfile setMpnId(String mpnId) {
		this.mpnId = mpnId;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public MPNProfile setProfileType(String profileType) {
		this.profileType = profileType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public MPNProfile setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public MPNProfile setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
