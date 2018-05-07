package org.springframework.social.partnercenter.api.utilities.impl;

import static java.time.ZoneId.of;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.social.partnercenter.serialization.Json.toJson;
import static org.springframework.social.partnercenter.time.PartnerCenterDateTimeFormatter.PARTNER_CENTER_UTC;
import static org.springframework.util.Assert.notNull;

import java.time.Instant;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.AuditRecord;
import org.springframework.social.partnercenter.api.customer.Address;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.api.utilities.CountryInformation;
import org.springframework.social.partnercenter.api.utilities.UtilityOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UtilityTemplate extends AbstractTemplate implements UtilityOperations{
	private final RestResource restResource;

	public UtilityTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}

	@Override
	public ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId) {
		notNull(isoCodeId, "[Assertion failed] - isoCodeId argument must be null");
		return restResource.request()
				.pathSegment("countryvalidationrules", isoCodeId)
				.get(CountryInformation.class);
	}

	@Override
	public Boolean isDomainAvailable(String domain) {
		notNull(domain, "[Assertion failed] - domain argument must be null");
		try {
			return !restResource.request()
					.pathSegment("domains", domain)
					.noRetry()
					.head().getStatusCode()
					.equals(OK);
		} catch (ApiFaultException fault){
			return fault.getHttpStatus().equals(NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Boolean> validateAddress(Address address) {
		notNull(address, "[Assertion failed] - address argument must be null");
		try {
			return restResource.request()
					.pathSegment("validations", "address")
					.post(address, Boolean.class);
		} catch (ApiFaultException fault) {
			if (Objects.equals(fault.getStatusCode(), HttpStatus.BAD_REQUEST)) {
				return ok(false);
			} else {
				throw fault;
			}
		}
	}

	@Override
	public ResponseEntity deleteCustomer(String customerId) {
		notNull(customerId, "[Assertion failed] - customerId argument must be null");
		return restResource.request()
				.pathSegment("customers", customerId)
				.delete();
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate) {
		notNull(startDate, "[Assertion failed] - startDate argument must be null");
		notNull(endDate, "[Assertion failed] - endDate argument must be null");
		return restResource.request()
				.pathSegment("auditrecords")
				.queryParam("startDate", startDate.atZone(of("UTC")).format(PARTNER_CENTER_UTC))
				.queryParam("endDate", endDate.atZone(of("UTC")).format(PARTNER_CENTER_UTC))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AuditRecord>>() {});
	}
}
