package org.springframework.social.partnercenter.api.utilities.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.social.partnercenter.api.customer.Address;
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
		notNull(isoCodeId, "isoCodeId");
		return restResource.request()
				.pathSegment("countryvalidationrules", isoCodeId)
				.get(CountryInformation.class);
	}

	@Override
	public Boolean isDomainAvailable(String domain) {
		notNull(domain, "domain");
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
		notNull(address, "address");
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
	public ResponseEntity validateAddressWithErrorMessage(Address address) {
		notNull(address, "address");

		return restResource.request()
			.pathSegment("validations", "address")
			.post(address, String.class);
	}

	@Override
	public ResponseEntity deleteCustomer(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment("customers", customerId)
				.delete();
	}
}
