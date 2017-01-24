package org.springframework.social.partnercenter.api.customer;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerPartnerRelationship {
	ADVISOR("advisor"),
	MICROSOFT_SUPPORT("microsoft_support"),
	NONE("none"),
	RESELLER("reseller"),
	SYNDICATIOMN("syndication");

	private String jsonValue;

	CustomerPartnerRelationship(final String json) {
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
