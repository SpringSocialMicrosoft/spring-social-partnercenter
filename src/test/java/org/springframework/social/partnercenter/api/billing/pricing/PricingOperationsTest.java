package org.springframework.social.partnercenter.api.billing.pricing;

import static org.springframework.social.partnercenter.test.stubs.PricingOperationsStubs.given_getAzurePricing_200_OK;

import java.net.URI;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Locale;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.billing.pricing.impl.PricingTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class PricingOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	@Test
	public void testGetAzurePricing_whenCalled_thenResponseIsParsedCorrectly() {
		given_getAzurePricing_200_OK();
		final PricingTemplate pricingTemplate = new PricingTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), URI.create("http://localhost:8080/v1/ratecards/azure")), true);
		final ResponseEntity<AzureResourcePricing> azurePricing = pricingTemplate.getAzurePricing();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(azurePricing.getBody().getOfferTerms().get(0).getEffectiveDate()).isEqualByComparingTo(Instant.parse("2014-01-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getMeters().get(0).getEffectiveDate()).isEqualTo(ZonedDateTime.parse("2015-10-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getMeters().get(1).getEffectiveDate()).isEqualTo(ZonedDateTime.parse("2015-11-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getLocale()).isEqualTo(Locale.US);

		});
	}
}
