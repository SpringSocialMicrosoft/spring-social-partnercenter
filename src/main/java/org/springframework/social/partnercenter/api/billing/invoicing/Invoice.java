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

	public String getId() {
		return id;
	}

	public Invoice setId(String id) {
		this.id = id;
		return this;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public Invoice setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
		return this;
	}

	public double getTotalCharges() {
		return totalCharges;
	}

	public Invoice setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
		return this;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public Invoice setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
		return this;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public Invoice setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		return this;
	}

	public String getPdfDownloadLink() {
		return pdfDownloadLink;
	}

	public Invoice setPdfDownloadLink(String pdfDownloadLink) {
		this.pdfDownloadLink = pdfDownloadLink;
		return this;
	}

	public List<InvoiceLineItem> getInvoiceDetails() {
		return invoiceDetails;
	}

	public Invoice setInvoiceDetails(List<InvoiceLineItem> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
		return this;
	}
}
