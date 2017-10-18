package org.springframework.social.partnercenter.api.audit.impl;

import static java.time.ZoneOffset.UTC;
import static org.springframework.social.partnercenter.time.PartnerCenterDateTimeFormatter.PARTNER_CENTER_SHORT;

import java.time.Instant;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.AuditOperations;
import org.springframework.social.partnercenter.api.audit.AuditRecord;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.api.customer.query.Operator;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AuditTemplate implements AuditOperations {
	private final RestResource restResource;

	public AuditTemplate(RestResource restResource) {
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate) {
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate, Instant endDate) {
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCompanyName(Instant startDate, Instant endDate, String companyName) {
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("filter", Filter.builder().operator(Operator.EQUALS).field("CompanyName").value(companyName))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCustomerId(Instant startDate, Instant endDate, String customerId) {
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("filter", Filter.builder().operator(Operator.EQUALS).field("CustomerId").value(customerId))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByResourceType(Instant startDate, Instant endDate, String resourceType) {
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("filter", Filter.builder().operator(Operator.EQUALS).field("ResourceType").value(resourceType))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}
}
