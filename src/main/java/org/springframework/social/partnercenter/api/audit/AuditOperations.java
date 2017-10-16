package org.springframework.social.partnercenter.api.audit;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AuditOperations {
	ResponseEntity<PartnerCenterResponse<PartnerAuditRecord>> getAuditRecordForPartner(Instant startDate);
	ResponseEntity<PartnerCenterResponse<PartnerAuditRecord>> getAuditRecordForPartner(Instant startDate, Instant endDate);
	ResponseEntity<PartnerCenterResponse<PartnerAuditRecord>> getAuditRecordByCompanyName(Instant startDate, Instant endDate, String companyName);
	ResponseEntity<PartnerCenterResponse<PartnerAuditRecord>> getAuditRecordByCustomerId(Instant startDate, Instant endDate, String customerId);
	ResponseEntity<PartnerCenterResponse<PartnerAuditRecord>> getAuditRecordByResoureType(Instant startDate, Instant endDate, String resourceType);
}
