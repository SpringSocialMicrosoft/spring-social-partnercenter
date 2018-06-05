package org.springframework.social.partnercenter.api.audit.impl;

import static java.time.ZoneOffset.UTC;
import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;
import static org.springframework.social.partnercenter.time.PartnerCenterDateTimeFormatter.PARTNER_CENTER_SHORT;

import java.time.Instant;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.AuditOperations;
import org.springframework.social.partnercenter.api.audit.AuditRecord;
import org.springframework.social.partnercenter.api.audit.AuditRecordSearchField;
import org.springframework.social.partnercenter.api.audit.ResourceType;
import org.springframework.social.partnercenter.api.query.Filter;
import org.springframework.social.partnercenter.api.query.Operator;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AuditTemplate implements AuditOperations {
	private final RestResource restResource;

	public AuditTemplate(RestResource restResource) {
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate) {
		notNull(startDate, "startDate");
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate, Instant endDate) {
		notNull(startDate, "startDate");
		notNull(endDate, "endDate");
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCompanyName(Instant startDate, Instant endDate, String companyName) {
		notNull(startDate, "startDate");
		notNull(endDate, "endDate");
		notNull(companyName, "companyName");
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("filter", Filter.create(AuditRecordSearchField.COMPANY_NAME, Operator.EQUALS, companyName))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCustomerId(Instant startDate, Instant endDate, String customerId) {
		notNull(startDate, "startDate");
		notNull(endDate, "endDate");
		notNull(customerId, "customerId");
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
					.queryParam("filter", Filter.create(AuditRecordSearchField.CUSTOMER_ID, Operator.EQUALS, customerId))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByResourceType(Instant startDate, Instant endDate, ResourceType resourceType) {
		notNull(startDate, "startDate");
		notNull(endDate, "endDate");
		notNull(resourceType, "resourceType");
		return restResource.request()
				.queryParam("startDate", startDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("endDate", endDate.atZone(UTC).format(PARTNER_CENTER_SHORT))
				.queryParam("filter", Filter.create(AuditRecordSearchField.RESOURCE_TYPE, Operator.EQUALS, resourceType.getValue()))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}
}
