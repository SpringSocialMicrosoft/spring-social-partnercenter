package org.springframework.social.partnercenter.api.agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AgreementType {
	MICROSOFT_CLOUD_AGREEMENT("MicrosoftCloudAgreement", "998b88de-aa99-4388-a42c-1b3517d49490"),
	MICROSOFT_CUSTOMER_AGREEMENT("MicrosoftCustomerAgreement", "117a77b0-9360-443b-8795-c6dedc750cf9");

	private String jsonValue;
	private String templateId;
}
