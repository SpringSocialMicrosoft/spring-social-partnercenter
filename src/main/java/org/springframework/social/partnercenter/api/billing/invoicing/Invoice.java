package org.springframework.social.partnercenter.api.billing.invoicing;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class Invoice {
	private String id;
	private String invoiceDate;
	private double totalCharges;
	private double paidAmount;
	private String currencyCode;
	private String pdfDownloadLink;
	private String currencySymbol;
	private List<InvoiceLineItem> invoiceDetails;
	private List<Invoice> amendments;
	private DocumentType documentType;
	private String amendsOf;
	private String invoiceType;

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

	public List<Invoice> getAmendments() {
		return amendments;
	}

	public void setAmendments(List<Invoice> amendments) {
		this.amendments = amendments;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getAmendsOf() {
		return amendsOf;
	}

	public void setAmendsOf(String amendsOf) {
		this.amendsOf = amendsOf;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
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
