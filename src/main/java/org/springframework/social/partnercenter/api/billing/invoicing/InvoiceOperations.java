package org.springframework.social.partnercenter.api.billing.invoicing;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface InvoiceOperations {
	ResponseEntity<PartnerCenterResponse<Invoice>> getInvoices();
	ResponseEntity<Invoice> getById(String id);
}
