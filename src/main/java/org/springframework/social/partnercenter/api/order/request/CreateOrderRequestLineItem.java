package org.springframework.social.partnercenter.api.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	public void setLineItemNumber(int lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public CreateOrderRequestLineItemBuilder builder(){
		return new CreateOrderRequestLineItemBuilder();
	}

	public static class CreateOrderRequestLineItemBuilder{
		private int lineItemNumber;
		private String offerId;
		private String friendlyName;
		private String quantity;

		public CreateOrderRequestLineItemBuilder lineItemNumber(int lineItemNumber) {
			this.lineItemNumber = lineItemNumber;
			return this;
		}

		public CreateOrderRequestLineItemBuilder offerId(String offerId) {
			this.offerId = offerId;
			return this;
		}

		public CreateOrderRequestLineItemBuilder friendlyName(String friendlyName) {
			this.friendlyName = friendlyName;
			return this;
		}

		public CreateOrderRequestLineItemBuilder quantity(String quantity) {
			this.quantity = quantity;
			return this;
		}

		public CreateOrderRequestLineItem build(){
			CreateOrderRequestLineItem createOrderRequestLineItem = new CreateOrderRequestLineItem();
			createOrderRequestLineItem.setLineItemNumber(lineItemNumber);
			createOrderRequestLineItem.setFriendlyName(friendlyName);
			createOrderRequestLineItem.setOfferId(offerId);
			createOrderRequestLineItem.setQuantity(quantity);
			return createOrderRequestLineItem;
		}
	}
}
