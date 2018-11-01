package org.springframework.social.partnercenter.api.billing.pricing;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.social.partnercenter.test.stubs.PricingOperationsStubs.given_getAzurePricingForCurrencyAndRegion_returningRealRates;
import static org.springframework.social.partnercenter.test.stubs.PricingOperationsStubs.given_getAzurePricing_200_OK;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.stream.IntStream;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.billing.pricing.impl.PricingTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.stubs.StubURI;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class PricingOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());

	@Test
	public void testGetAzurePricing_whenCalled_thenResponseIsParsedCorrectly() {
		given_getAzurePricing_200_OK();
		final PricingTemplate pricingTemplate = new PricingTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), StubURI.baseURI(wireMockRule.port(), "v1", "ratecards", "azure")), true);
		final ResponseEntity<AzureResourcePricing> azurePricing = pricingTemplate.getAzurePricing();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(azurePricing.getBody().getOfferTerms().get(0).getEffectiveDate()).isEqualByComparingTo(Instant.parse("2014-01-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getMeters().get(0).getEffectiveDate()).isEqualTo(ZonedDateTime.parse("2015-10-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getMeters().get(1).getEffectiveDate()).isEqualTo(ZonedDateTime.parse("2015-11-01T00:00:00Z"));
			softly.assertThat(azurePricing.getBody().getLocale()).isEqualTo(Locale.US);
		});
	}

	@Test
	public void testGetAzurePricingWithCountryAndLocale_whenCalled_thenResponseIsParsedCorrectlyWithComplexRates() {
		given_getAzurePricingForCurrencyAndRegion_returningRealRates("EUR", "DE");

		final PricingTemplate pricingTemplate = new PricingTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), StubURI.baseURI(wireMockRule.port(), "v1", "ratecards", "azure")), true);
		final ResponseEntity<AzureResourcePricing> azurePricing = pricingTemplate.getAzurePricing("EUR", "DE", Locale.GERMANY);

		String[] expectedRates = new String[] {"0.055193985123456789", "2.8799074485", "3.35178018", "0.1003527", "16.185919239225"};

		SoftAssertions.assertSoftly(softly -> {
			IntStream.range(0, expectedRates.length).forEach(rateIndex -> {
				BigDecimal actualRate = azurePricing.getBody().getMeters().get(rateIndex).getRates().get("0");
				BigDecimal expectedRate = new BigDecimal(expectedRates[rateIndex]);
				softly.assertThat(actualRate).isEqualTo(expectedRate);
			});
		});
	}
}
