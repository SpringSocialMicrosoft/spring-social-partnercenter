package org.springframework.social.partnercenter.api.billing.invoicing;

import org.springframework.social.partnercenter.api.ResourceBaseWithLinks;
import org.springframework.social.partnercenter.api.StandardResourceLinks;

public class InvoiceLineItem extends ResourceBaseWithLinks<StandardResourceLinks> {
	private String invoiceLineItemType;
	private String billingProvider;

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
}
