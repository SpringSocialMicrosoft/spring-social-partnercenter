package org.springframework.social.partnercenter.model.order;

import java.util.Map;

public class LineItem {
	private int lineItemNumber;
	private String offerId;
	private String friendlyName;
	private String quantity;
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

	public String getFriendlyName() {
		return friendlyName;
	}

	public LineItem setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public String getQuantity() {
		return quantity;
	}

	public LineItem setQuantity(String quantity) {
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
}
