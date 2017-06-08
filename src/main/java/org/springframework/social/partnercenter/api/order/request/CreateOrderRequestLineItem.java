package org.springframework.social.partnercenter.api.order.request;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CreateOrderRequestLineItem {
	private Integer lineItemNumber;
	private String offerId;
	private String parentSubscriptionId;
	private String friendlyName;
	private Integer quantity;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String partnerIdOnRecord;

	public Integer getLineItemNumber() {
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

	public String getParentSubscriptionId() {
		return parentSubscriptionId;
	}

	public void setParentSubscriptionId(String parentSubscriptionId) {
		this.parentSubscriptionId = parentSubscriptionId;
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

	public String getPartnerIdOnRecord() {
		return partnerIdOnRecord;
	}

	public void setPartnerIdOnRecord(String partnerIdOnRecord) {
		this.partnerIdOnRecord = partnerIdOnRecord;
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
		private String parentSubscriptionId;
		private String friendlyName;
		private int quantity;
		private String partnerIdOnRecord;

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

		public CreateOrderRequestLineItemBuilder parentSubscriptionId(String parentSubscriptionId) {
			this.parentSubscriptionId = parentSubscriptionId;
			return this;
		}

		public CreateOrderRequestLineItemBuilder partnerIdOnRecord(String partnerIdOnRecord) {
			this.partnerIdOnRecord = partnerIdOnRecord;
			return this;
		}

		public CreateOrderRequestLineItem build(){
			CreateOrderRequestLineItem createOrderRequestLineItem = new CreateOrderRequestLineItem();
			createOrderRequestLineItem.setFriendlyName(friendlyName);
			createOrderRequestLineItem.setOfferId(offerId);
			createOrderRequestLineItem.setParentSubscriptionId(parentSubscriptionId);
			createOrderRequestLineItem.setQuantity(quantity);
			createOrderRequestLineItem.setPartnerIdOnRecord(partnerIdOnRecord);
			if (nonNull(lineItemNumber)) {
				createOrderRequestLineItem.setLineItemNumber(lineItemNumber);
			}
			return createOrderRequestLineItem;
		}
	}
}
