package org.springframework.social.partnercenter.api.customer;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

	@JsonProperty("id")
	private String id;
	@JsonProperty("CompanyProfile")
	private CompanyProfile companyProfile;
	@JsonProperty("BillingProfile")
	private BillingProfile billingProfile;
	@JsonProperty("relationshipToPartner")
	private String relationshipToPartner;
	@JsonProperty("userCredentials")
	private UserCredentials userCredentials;
	@JsonProperty("attributes")
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}

	public BillingProfile getBillingProfile() {
		return billingProfile;
	}

	public void setBillingProfile(BillingProfile billingProfile) {
		this.billingProfile = billingProfile;
	}

	public String getRelationshipToPartner() {
		return relationshipToPartner;
	}

	public void setRelationshipToPartner(String relationshipToPartner) {
		this.relationshipToPartner = relationshipToPartner;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
