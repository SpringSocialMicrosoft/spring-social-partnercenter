package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.social.partnercenter.test.Resource.parseFile;

public class AuditOperationsStubs {
	public static void given_getAuditRecord_200_OK() {
		stubFor(get(urlPathEqualTo("/v1/auditrecords"))
		.willReturn(aResponse()
				.withStatus(200)
				.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.withBody(parseFile("data/audit/record.json").getAsString())));
	}
}
