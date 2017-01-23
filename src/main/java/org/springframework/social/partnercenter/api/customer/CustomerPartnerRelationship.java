package org.springframework.social.partnercenter.api.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CustomerPartnerRelationship {
	@JsonProperty("advisor")
	ADVISOR,
	@JsonProperty("microsoft_support")
	MICROSOFT_SUPPORT,
	@JsonProperty("none")
	NONE,
	@JsonProperty("reseller")
	RESELLER,
	@JsonProperty("syndication")
	SYNDICATIOMN

}
