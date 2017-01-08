package org.springframework.social.partnercenter.api.profile;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.customer.Address;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingProfile {
	private String companyName;
	private Address address;
	private Contact primaryContact;
	private String purchaseOrderNumber;
	private String taxId;
	private String billingCurrency;
	private String profileType;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public String getCompanyName() {
		return companyName;
	}

	public BillingProfile setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public BillingProfile setAddress(Address address) {
		this.address = address;
		return this;
	}

	public Contact getPrimaryContact() {
		return primaryContact;
	}

	public BillingProfile setPrimaryContact(Contact primaryContact) {
		this.primaryContact = primaryContact;
		return this;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public BillingProfile setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
		return this;
	}

	public String getTaxId() {
		return taxId;
	}

	public BillingProfile setTaxId(String taxId) {
		this.taxId = taxId;
		return this;
	}

	public String getBillingCurrency() {
		return billingCurrency;
	}

	public BillingProfile setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
		return this;
	}

	public String getProfileType() {
		return profileType;
	}

	public BillingProfile setProfileType(String profileType) {
		this.profileType = profileType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public BillingProfile setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public BillingProfile setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
