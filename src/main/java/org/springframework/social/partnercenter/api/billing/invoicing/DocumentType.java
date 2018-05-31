package org.springframework.social.partnercenter.api.billing.invoicing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentType {
	NONE(0),
	INVOICE(1),
	VOID_NOTE(2),
	ADJUSTMENT_NOTE(3);

	private Integer value;

	DocumentType(Integer value) {
		this.value = value;
	}

	@JsonValue
	public Integer toOrdinalValue() {
		return value;
	}

	@JsonCreator
	public DocumentType value(Integer value) {
		this.value = value;
		return this;
	}
}
