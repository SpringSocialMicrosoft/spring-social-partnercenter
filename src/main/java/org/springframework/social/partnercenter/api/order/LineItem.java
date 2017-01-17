package org.springframework.social.partnercenter.api.order;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem {
	private int lineItemNumber;
	private String offerId;
	private String subscriptionId;
	private String friendlyName;
	private int quantity;
	private Map<String, Link> links;

	public int getLineItemNumber() {
		return lineItemNumber;
	}

	public LineItem setLineItemNumber(int lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
		return this;
	}

	public String getOfferId() {
		return offerId;
	}

	public LineItem setOfferId(String offerId) {
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

	public LineItem setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public LineItem setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public LineItem setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public static LineItemBuilder builder(){
		return new LineItemBuilder();
	}

	public static class LineItemBuilder{
		private int lineItemNumber;
		private String offerId;
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

		public LineItem build(){
			LineItem lineItem = new LineItem();
			lineItem.setLineItemNumber(lineItemNumber);
			lineItem.setFriendlyName(friendlyName);
			lineItem.setLinks(links);
			lineItem.setOfferId(offerId);
			lineItem.setQuantity(quantity);
			return lineItem;
		}
	}
}
