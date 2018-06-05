package org.springframework.social.partnercenter.api.audit;

import org.springframework.social.partnercenter.api.query.SearchField;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuditRecordSearchField implements SearchField{
	COMPANY_NAME("CompanyName"),
	CUSTOMER_ID("CustomerId"),
	RESOURCE_TYPE("ResourceType");

	private String stringValue;

	AuditRecordSearchField(String stringValue) {
		this.stringValue = stringValue;
	}

	@JsonValue
	public String asString(){
		return stringValue;
	}
}
