package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.springframework.social.partnercenter.test.Resource;

public class PricingOperationsStubs {
	public static void given_getAzurePricing_200_OK() {
		stubFor(get(urlEqualTo("/v1/ratecards/azure"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody(Resource.parseFile("data/pricing/ok.json").getAsString())));
	}
}
