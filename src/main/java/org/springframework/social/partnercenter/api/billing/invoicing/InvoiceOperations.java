package org.springframework.social.partnercenter.api.billing.invoicing;

import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface InvoiceOperations {
	PartnerCenterResponse<Invoice> getInvoices();
	Invoice getById(String id);
}
