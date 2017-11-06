package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.social.partnercenter.test.Resource;

public class SubscriptionOperationStubs {
	public static void given_getById_200_OK() {
		stubFor(get(anyUrl())
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody(Resource.parseFile("data/subscription/ok.json").getAsString())));
	}
	public static void given_getById_200_OK_with_short_date() {
		stubFor(get(anyUrl())
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withBody(Resource.parseFile("data/subscription/ok-date-shorter.json").getAsString())));
	}

	public static void given_patch_200_OK() {
		stubFor(patch(anyUrl())
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withBody(Resource.parseFile("data/subscription/ok.json").getAsString()))
		);
	}
}
