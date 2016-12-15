package org.springframework.social.partnercenter.api.customer.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRelationshipRequest {
	@JsonProperty
	private String url;
	@JsonProperty
	private Map<String, String> attributes;

	public String getUrl() {
		return url;
	}

	public CustomerRelationshipRequest setUrl(String url) {
		this.url = url;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public CustomerRelationshipRequest setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
