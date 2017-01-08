package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;

public class SupportProfile {
	private String email;
	private String telephone;
	private String website;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getEmail() {
		return email;
	}

	public SupportProfile setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getTelephone() {
		return telephone;
	}

	public SupportProfile setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public String getWebsite() {
		return website;
	}

	public SupportProfile setWebsite(String website) {
		this.website = website;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public SupportProfile setProfileType(String profileType) {
		this.profileType = profileType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public SupportProfile setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public SupportProfile setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
