package org.springframework.social.partnercenter.api.billing.invoicing;

import org.springframework.social.partnercenter.api.PartnerCenterPaginatedResponse;

public interface InvoiceOperations {
	PartnerCenterPaginatedResponse<Invoice> getInvoices();
	Invoice getById(String id);
}
