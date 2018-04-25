package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.social.partnercenter.api.Link;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Partner {
	private String partnerName;
	private String mpnId;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getPartnerName() {
		return partnerName;
	}

	public Partner setPartnerName(String partnerName) {
		this.partnerName = partnerName;
		return this;
	}

	public String getMpnId() {
		return mpnId;
	}

	public Partner setMpnId(String mpnId) {
		this.mpnId = mpnId;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public Partner setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Partner setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
}
