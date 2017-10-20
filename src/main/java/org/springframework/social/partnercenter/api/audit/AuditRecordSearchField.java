package org.springframework.social.partnercenter.api.audit;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuditRecordSearchField {
	COMPANY_NAME("CompanyName");

	private String stringValue;

	AuditRecordSearchField(String stringValue) {
		this.stringValue = stringValue;
	}

	@JsonValue
	public String toValue(){
		return stringValue;
	}
}
