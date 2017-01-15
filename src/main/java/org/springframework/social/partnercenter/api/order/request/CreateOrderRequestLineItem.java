package org.springframework.social.partnercenter.api.order.request;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private int quantity;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public static CreateOrderRequestLineItemBuilder lineItemBuilder(){
		return new CreateOrderRequestLineItemBuilder();
	}

	@JsonIgnore
	public static LineItemListBuilder lineItemListBuilder(){
		return new LineItemListBuilder();
	}

	public static class LineItemListBuilder{
		List<CreateOrderRequestLineItem> lineItems;

		public LineItemListBuilder() {
			this.lineItems = new ArrayList<>();
		}

		public LineItemListBuilder addLineItem(CreateOrderRequestLineItemBuilder builder){
			CreateOrderRequestLineItem build = builder.build();
			build.setLineItemNumber(builder.lineItemNumber != null ? build.getLineItemNumber() : calculateNextIndex());
			lineItems.add(build);
			return this;
		}

		private int calculateNextIndex(){
			return lineItems.stream()
					.mapToInt(CreateOrderRequestLineItem::getLineItemNumber)
					.max().orElse(-1) + 1;
		}

		public List<CreateOrderRequestLineItem> build(){
			return lineItems;
		}
	}

	public static class CreateOrderRequestLineItemBuilder{
		private Integer lineItemNumber;
		private String offerId;
		private String friendlyName;
		private int quantity;

		public CreateOrderRequestLineItemBuilder lineItemNumber(Integer lineItemNumber) {
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

		public CreateOrderRequestLineItemBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public CreateOrderRequestLineItem build(){
			CreateOrderRequestLineItem createOrderRequestLineItem = new CreateOrderRequestLineItem();
			createOrderRequestLineItem.setFriendlyName(friendlyName);
			createOrderRequestLineItem.setOfferId(offerId);
			createOrderRequestLineItem.setQuantity(quantity);
			if (nonNull(lineItemNumber)) {
				createOrderRequestLineItem.setLineItemNumber(lineItemNumber);
			}
			return createOrderRequestLineItem;
		}
	}
}
