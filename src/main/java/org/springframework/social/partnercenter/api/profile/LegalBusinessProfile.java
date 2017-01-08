package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.customer.Address;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalBusinessProfile {
	private String companyName;
	private Address address;
	private Contact primaryContact;
	private Address companyApproverAddress;
	private SupportProfile companyApproverEmail;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getCompanyName() {
		return companyName;
	}

	public LegalBusinessProfile setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public LegalBusinessProfile setAddress(Address address) {
		this.address = address;
		return this;
	}

	public Contact getPrimaryContact() {
		return primaryContact;
	}

	public LegalBusinessProfile setPrimaryContact(Contact primaryContact) {
		this.primaryContact = primaryContact;
		return this;
	}

	public Address getCompanyApproverAddress() {
		return companyApproverAddress;
	}

	public LegalBusinessProfile setCompanyApproverAddress(Address companyApproverAddress) {
		this.companyApproverAddress = companyApproverAddress;
		return this;
	}

	public SupportProfile getCompanyApproverEmail() {
		return companyApproverEmail;
	}

	public LegalBusinessProfile setCompanyApproverEmail(SupportProfile companyApproverEmail) {
		this.companyApproverEmail = companyApproverEmail;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public LegalBusinessProfile setProfileType(String profileType) {
		this.profileType = profileType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public LegalBusinessProfile setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public LegalBusinessProfile setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
