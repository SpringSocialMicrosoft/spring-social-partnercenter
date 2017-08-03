package org.springframework.social.partnercenter.api.order;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class OrderLineItem {
	private Integer lineItemNumber;
	private String offerId;
	private String subscriptionId;
	private String parentSubscriptionId;
	private String partnerIdOnRecord;
	private String friendlyName;
	private Integer quantity;
	private Map<String, Link> links;

	public Integer getLineItemNumber() {
		return lineItemNumber;
	}

	public OrderLineItem setLineItemNumber(Integer lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
		return this;
	}

	public String getOfferId() {
		return offerId;
	}

	public OrderLineItem setOfferId(String offerId) {
		this.offerId = offerId;
		return this;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public OrderLineItem setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public OrderLineItem setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public OrderLineItem setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public String getParentSubscriptionId() {
		return parentSubscriptionId;
	}

	public void setParentSubscriptionId(String parentSubscriptionId) {
		this.parentSubscriptionId = parentSubscriptionId;
	}

	public String getPartnerIdOnRecord() {
		return partnerIdOnRecord;
	}

	public void setPartnerIdOnRecord(String partnerIdOnRecord) {
		this.partnerIdOnRecord = partnerIdOnRecord;
	}

	@JsonIgnore
	public static LineItemBuilder builder(){
		return new LineItemBuilder();
	}

	public static class LineItemBuilder{
		private int lineItemNumber;
		private String offerId;
		private String subscriptionId;
		private String parentSubscriptionId;
		private String partnerIdOnRecord;
		private String friendlyName;
		private int quantity;
		private Map<String, Link> links;

		public LineItemBuilder lineItemNumber(int lineItemNumber) {
			this.lineItemNumber = lineItemNumber;
			return this;
		}

		public LineItemBuilder offerId(String offerId) {
			this.offerId = offerId;
			return this;
		}

		public LineItemBuilder subscriptionId(String subscriptionId) {
			this.subscriptionId = subscriptionId;
			return this;
		}

		public LineItemBuilder friendlyName(String friendlyName) {
			this.friendlyName = friendlyName;
			return this;
		}

		public LineItemBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public LineItemBuilder links(Map<String, Link> links) {
			this.links = links;
			return this;
		}

		public LineItemBuilder parentSubscriptionId(String parentSubscriptionId) {
			this.parentSubscriptionId = parentSubscriptionId;
			return this;
		}

		public LineItemBuilder partnerIdOnRecord(String partnerIdOnRecord) {
			this.partnerIdOnRecord = partnerIdOnRecord;
			return this;
		}

		public OrderLineItem build(){
			OrderLineItem lineItem = new OrderLineItem();
			lineItem.setLineItemNumber(lineItemNumber);
			lineItem.setFriendlyName(friendlyName);
			lineItem.setLinks(links);
			lineItem.setSubscriptionId(subscriptionId);
			lineItem.setOfferId(offerId);
			lineItem.setQuantity(quantity);
			lineItem.setParentSubscriptionId(parentSubscriptionId);
			lineItem.setPartnerIdOnRecord(partnerIdOnRecord);
			return lineItem;
		}
	}
}
