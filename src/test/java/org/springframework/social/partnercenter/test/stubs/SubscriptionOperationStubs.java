package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import org.springframework.social.partnercenter.test.Resource;

public class SubscriptionOperationStubs {
	public static void given_getById_200_OK() {
		stubFor(get(anyUrl())
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody(Resource.parseFile("data/subscription/ok.json").getAsString())));
	}
}
