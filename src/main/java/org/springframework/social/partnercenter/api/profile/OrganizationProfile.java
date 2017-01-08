package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.customer.Address;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationProfile {
	private String id;
	private String companyName;
	private Address defaultAddress;
	private String tenantId;
	private String domain;
	private String email;
	private String language;
	private String culture;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public OrganizationProfile setId(String id) {
		this.id = id;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public OrganizationProfile setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Address getDefaultAddress() {
		return defaultAddress;
	}

	public OrganizationProfile setDefaultAddress(Address defaultAddress) {
		this.defaultAddress = defaultAddress;
		return this;
	}

	public String getTenantId() {
		return tenantId;
	}

	public OrganizationProfile setTenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	public String getDomain() {
		return domain;
	}

	public OrganizationProfile setDomain(String domain) {
		this.domain = domain;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public OrganizationProfile setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public OrganizationProfile setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getCulture() {
		return culture;
	}

	public OrganizationProfile setCulture(String culture) {
		this.culture = culture;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public OrganizationProfile setProfileType(String profileType) {
		this.profileType = profileType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public OrganizationProfile setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public OrganizationProfile setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
