package org.springframework.social.partnercenter.api.order.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	public static CreateOrderRequestBuilder createOrderRequestBuilder(){
		return new CreateOrderRequestBuilder();
	}

	public static class CreateOrderRequestBuilder{
		private String referenceCustomerId;
		private List<CreateOrderRequestLineItem> lineItems = new ArrayList<>();
		private Map<String, String> attributes;

		public CreateOrderRequestBuilder referenceCustomerId(String referenceCustomerId) {
			this.referenceCustomerId = referenceCustomerId;
			return this;
		}

		public CreateOrderRequestBuilder lineItems(List<CreateOrderRequestLineItem> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public CreateOrderRequestBuilder addLineItem(CreateOrderRequestLineItem lineItem){
			lineItem.setLineItemNumber(lineItem.getLineItemNumber() != null ? lineItem.getLineItemNumber() : calculateNextIndex());
			lineItems.add(lineItem);
			return this;
		}

		private int calculateNextIndex(){
			return lineItems.stream()
					.mapToInt(CreateOrderRequestLineItem::getLineItemNumber)
					.max().orElse(-1) + 1;
		}

		public CreateOrderRequestBuilder attributes(Map<String, String> attributes) {
			this.attributes = attributes;
			return this;
		}

		@JsonIgnore
		public CreateOrderRequest build(){
			CreateOrderRequest createOrderRequest = new CreateOrderRequest();
			createOrderRequest.setReferenceCustomerId(referenceCustomerId);
			createOrderRequest.setAttributes(attributes);
			createOrderRequest.setLineItems(lineItems);
			return createOrderRequest;
		}
	}
}
