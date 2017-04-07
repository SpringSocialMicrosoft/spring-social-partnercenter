package org.springframework.social.partnercenter.api.utilities.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.ApiFaultException;
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
		return restResource.request()
				.pathSegment("countryvalidationrules", isoCodeId)
				.get(CountryInformation.class);
	}

	@Override
	public Boolean isDomainAvailable(String domainId) {
		try {
			return !restResource.request()
					.pathSegment("validations", "checkdomainavailability", domainId)
					.head().getStatusCode().equals(OK);
		} catch (ApiFaultException fault){
			return fault.getHttpStatus().equals(NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity deleteCustomer(String customerId) {
		return restResource.request()
				.pathSegment("customers", customerId)
				.delete();
	}
}
