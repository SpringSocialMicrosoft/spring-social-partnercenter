package org.springframework.social.partnercenter.api.customer;

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

	@JsonProperty("id")
	private String id;
	@JsonProperty("commerceId")
	private String commerceId;
	@JsonProperty("companyProfile")
	private CustomerCompanyProfile companyProfile;
	@JsonProperty("billingProfile")
	private CustomerBillingProfile billingProfile;
	@JsonProperty("relationshipToPartner")
	private CustomerPartnerRelationship relationshipToPartner;
	@JsonProperty("userCredentials")
	private UserCredentials userCredentials;
	@JsonProperty("customDomains")
	private Collection<String> customDomains;
	@JsonProperty("attributes")
	private Map<String, String> attributes;
	@JsonProperty("allowDelegatedAccess")
	private boolean allowDelegatedAccess;
	@JsonProperty("associatedPartnerId")
	private String associatedPartnerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}

	public CustomerCompanyProfile getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(CustomerCompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}

	public CustomerBillingProfile getBillingProfile() {
		return billingProfile;
	}

	public void setBillingProfile(CustomerBillingProfile billingProfile) {
		this.billingProfile = billingProfile;
	}

	public CustomerPartnerRelationship getRelationshipToPartner() {
		return relationshipToPartner;
	}

	public void setRelationshipToPartner(CustomerPartnerRelationship relationshipToPartner) {
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

	public Collection<String> getCustomDomains() {
		return customDomains;
	}

	public void setCustomDomains(Collection<String> customDomains) {
		this.customDomains = customDomains;
	}

	public boolean isAllowDelegatedAccess() { return allowDelegatedAccess; }

	public void setAllowDelegatedAccess(boolean allowDelegatedAccess) { this.allowDelegatedAccess = allowDelegatedAccess; }

	public String getAssociatedPartnerId() { return associatedPartnerId; }

	public void setAssociatedPartnerId(String associatedPartnerId) { this.associatedPartnerId = associatedPartnerId; }
}
