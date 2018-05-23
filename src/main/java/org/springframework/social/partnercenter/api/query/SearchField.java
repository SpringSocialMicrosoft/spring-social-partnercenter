package org.springframework.social.partnercenter.api.query;

import com.fasterxml.jackson.annotation.JsonValue;

public interface SearchField {
	@JsonValue
	String asString();
}
