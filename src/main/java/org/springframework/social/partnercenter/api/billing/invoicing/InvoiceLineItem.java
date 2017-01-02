package org.springframework.social.partnercenter.api.billing.invoicing;

import java.util.List;
import java.util.Map;

import org.springframework.social.partnercenter.api.order.Link;

public class InvoiceLineItem {
	private String invoiceLineItemType;
	private String billingProvider;
	private List<Link> links;
	private Map<String, String> attributes;

	public String getInvoiceLineItemType() {
		return invoiceLineItemType;
	}

	public InvoiceLineItem setInvoiceLineItemType(String invoiceLineItemType) {
		this.invoiceLineItemType = invoiceLineItemType;
		return this;
	}

	public String getBillingProvider() {
		return billingProvider;
	}

	public InvoiceLineItem setBillingProvider(String billingProvider) {
		this.billingProvider = billingProvider;
		return this;
	}

	public List<Link> getLinks() {
		return links;
	}

	public InvoiceLineItem setLinks(List<Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public InvoiceLineItem setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
