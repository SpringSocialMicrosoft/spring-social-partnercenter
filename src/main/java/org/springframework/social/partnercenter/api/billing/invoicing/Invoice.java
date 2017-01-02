package org.springframework.social.partnercenter.api.billing.invoicing;

import java.util.List;

public class Invoice {
	private String id;
	private String invoiceDate;
	private double totalCharges;
	private double paidAmount;
	private String currencyCode;
	private String pdfDownloadLink;
	private List<InvoiceLineItem> invoiceDetails;
}
