package org.springframework.social.partnercenter.api.audit;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AuditOperations {
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate, Instant endDate);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCompanyName(Instant startDate, Instant endDate, String companyName);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCustomerId(Instant startDate, Instant endDate, String customerId);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByResourceType(Instant startDate, Instant endDate, String resourceType);
}
