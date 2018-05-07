package org.springframework.social.partnercenter.api.billing.pricing.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.billing.pricing.AzureResourcePricing;
import org.springframework.social.partnercenter.api.billing.pricing.PricingOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PricingTemplate extends AbstractTemplate implements PricingOperations {
	private RestResource restResource;

	public PricingTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<AzureResourcePricing> getAzurePricing() {
		return restResource.request()
				.get(AzureResourcePricing.class);
	}

	@Override
	public ResponseEntity<AzureResourcePricing> getAzurePricing(String currency, String region) {
		notNull(currency, "[Assertion failed] - currency argument must be null");
		notNull(region, "[Assertion failed] - region argument must be null");
		return restResource.request()
				.queryParam("currency", currency)
				.queryParam("region", region)
				.get(AzureResourcePricing.class);
	}

	@Override
	public ResponseEntity<AzureResourcePricing> getAzurePricing(String currency, String region, Locale locale) {
		notNull(currency, "[Assertion failed] - currency argument must be null");
		notNull(region, "[Assertion failed] - region argument must be null");
		notNull(locale, "[Assertion failed] - locale argument must be null");
		return restResource.request()
				.queryParam("currency", currency)
				.queryParam("region", region)
				.header("X-Locale", locale.toLanguageTag())
				.get(AzureResourcePricing.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
