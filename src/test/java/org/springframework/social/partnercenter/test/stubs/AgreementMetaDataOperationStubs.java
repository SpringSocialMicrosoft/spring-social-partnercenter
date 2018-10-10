package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.social.partnercenter.test.Resource;

public class AgreementMetaDataOperationStubs {

	public static void given_getAgreementMetadata_200_OK() {
		stubFor(get(anyUrl())
				        .willReturn(aResponse()
						                    .withStatus(200)
						                    .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						                    .withBody(Resource.parseFile("data/agreement/get-agreement-metadata.json").getAsString())));
	}
}
