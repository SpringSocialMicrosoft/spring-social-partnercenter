package org.springframework.social.partnercenter.api.order.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {
	@JsonProperty("ReferenceCustomerId")
	private String referenceCustomerId;
	@JsonProperty("LineItems")
	private List<CreateOrderRequestLineItem> lineItems;
	@JsonProperty("Attributes")
	private Map<String, String> attributes;

	public String getReferenceCustomerId() {
		return referenceCustomerId;
	}

	public CreateOrderRequest setReferenceCustomerId(String referenceCustomerId) {
		this.referenceCustomerId = referenceCustomerId;
		return this;
	}

	public List<CreateOrderRequestLineItem> getLineItems() {
		return lineItems;
	}

	public CreateOrderRequest setLineItems(List<CreateOrderRequestLineItem> lineItems) {
		this.lineItems = lineItems;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public CreateOrderRequest setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
