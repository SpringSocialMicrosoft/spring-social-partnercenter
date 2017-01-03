package org.springframework.social.partnercenter.api.billing.pricing;

import java.util.List;
import java.util.Map;

public class AzureResourcePricing {
	private String locale;
	private String currency;
	private boolean isTaxIncluded;
	private List<PricingMeters> meters;
	private List<OfferTerms> offerTerms;
	private Map<String, String> attributes;

	public String getLocale() {
		return locale;
	}

	public AzureResourcePricing setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public AzureResourcePricing setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public boolean isTaxIncluded() {
		return isTaxIncluded;
	}

	public AzureResourcePricing setTaxIncluded(boolean taxIncluded) {
		isTaxIncluded = taxIncluded;
		return this;
	}

	public List<PricingMeters> getMeters() {
		return meters;
	}

	public AzureResourcePricing setMeters(List<PricingMeters> meters) {
		this.meters = meters;
		return this;
	}

	public List<OfferTerms> getOfferTerms() {
		return offerTerms;
	}

	public AzureResourcePricing setOfferTerms(List<OfferTerms> offerTerms) {
		this.offerTerms = offerTerms;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public AzureResourcePricing setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
