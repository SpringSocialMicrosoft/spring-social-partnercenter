package org.springframework.social.partnercenter.api.customer;

import org.springframework.social.partnercenter.api.query.SearchField;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerSearchField implements SearchField{
	COMPANY_NAME("CompanyName"),
	DOMAIN("Domain"),
	INDIRECT_CLOUD_SOLUTION_PROVIDER("IndirectCloudSolutionProvider"),
	INDIRECT_RESELLER("IndirectReseller");

	private String value;

	CustomerSearchField(String json){
		this.value = json;
	}

	@JsonValue
	public String asString() {
		return this.value;
	}
}
