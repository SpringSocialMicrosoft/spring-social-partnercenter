package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.social.partnercenter.test.Resource;

public final class UsageOperationStubs {
	public static void given_getUtilizationRecords_200_OK(String customerId, String subscriptionId) {
		stubFor(get(urlPathEqualTo(format("/v1/customers/{0}/subscriptions/{1}/utilizations/azure", customerId, subscriptionId)))
				.willReturn(aResponse()
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withStatus(200)
						.withBody(Resource.parseFile("data/usage/get_utilization_OK.json").getAsStringFlattenedString())));
	}
}
