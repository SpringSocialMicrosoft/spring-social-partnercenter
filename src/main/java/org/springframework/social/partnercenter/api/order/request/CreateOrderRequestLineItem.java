package org.springframework.social.partnercenter.api.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequestLineItem {
	@JsonProperty("LineItemNumber")
	private int lineItemNumber;
	@JsonProperty("OfferId")
	private String offerId;
	@JsonProperty("FriendlyName")
	private String friendlyName;
	@JsonProperty("Quantity")
	private String quantity;

	public int getLineItemNumber() {
		return lineItemNumber;
	}

	public CreateOrderRequestLineItem setLineItemNumber(int lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
		return this;
	}

	public String getOfferId() {
		return offerId;
	}

	public CreateOrderRequestLineItem setOfferId(String offerId) {
		this.offerId = offerId;
		return this;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public CreateOrderRequestLineItem setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public String getQuantity() {
		return quantity;
	}

	public CreateOrderRequestLineItem setQuantity(String quantity) {
		this.quantity = quantity;
		return this;
	}
}
