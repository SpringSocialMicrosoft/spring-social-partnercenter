package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.social.partnercenter.test.Resource.parseFile;

import org.springframework.social.partnercenter.api.relationships.PartnerRelationshipType;

public class RelationshipOperationsStubs {
    public static void given_getPartnerRelationships_200_OK(PartnerRelationshipType relationshipType) {
        stubFor(get(urlEqualTo("/v1/relationships?relationship_type=".concat(relationshipType.urlEncodedValue())))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(parseFile("data/relationships/partnerRelationships.json").getAsString())
                ));
    }
}
