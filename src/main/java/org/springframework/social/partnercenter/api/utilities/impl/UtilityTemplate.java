package org.springframework.social.partnercenter.api.utilities.impl;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
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
	public CountryInformation getAddressFormattingRulesByMarket(String isoCodeId) {
		return restResource.request()
				.pathSegment("countryvalidationrules", isoCodeId)
				.get(CountryInformation.class);
	}

	@Override
	public Boolean isDomainAvailable(String domainId) {
		return restResource.request()
				.pathSegment("validations", "checkdomainavailability", domainId)
				.get(Boolean.class);
	}

	@Override
	public void deleteCustomer(String customerId) {
		restResource.request()
				.pathSegment("customers", customerId)
				.delete();
	}
}
