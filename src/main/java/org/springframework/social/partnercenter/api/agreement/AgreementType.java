package org.springframework.social.partnercenter.api.agreement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AgreementType {
	MICROSOFT_CLOUD_AGREEMENT("MicrosoftCloudAgreement");

	private String jsonValue;

	AgreementType(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	@JsonValue
	public String jsonValue() {
		return jsonValue;
	}
}
