package org.springframework.social.partnercenter.api.billing.pricing;

import java.util.Locale;

import org.springframework.http.ResponseEntity;

public interface PricingOperations {
	ResponseEntity<AzureResourcePricing> getAzurePricing();
	ResponseEntity<AzureResourcePricing> getAzurePricing(String currency, String region);
	ResponseEntity<AzureResourcePricing> getAzurePricing(String currency, String region, Locale locale);
}
