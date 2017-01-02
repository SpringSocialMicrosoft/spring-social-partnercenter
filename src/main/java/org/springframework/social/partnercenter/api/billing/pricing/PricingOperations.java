package org.springframework.social.partnercenter.api.billing.pricing;

public interface PricingOperations {
	AzureResourcePricing getAzurePricing();
	AzureResourcePricing getAzurePricing(String currency, String region);
}
