package org.springframework.social.partnercenter.api.relationship;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.social.partnercenter.test.stubs.RelationshipOperationsStubs.given_getPartnerRelationships_200_OK;
import static org.springframework.social.partnercenter.test.stubs.StubURI.baseURI;
import static org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory.createRestTemplate;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.relationships.AdminRelationshipOperations;
import org.springframework.social.partnercenter.api.relationships.PartnerRelationship;
import org.springframework.social.partnercenter.api.relationships.PartnerRelationshipType;
import org.springframework.social.partnercenter.api.relationships.impl.AdminRelationshipTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class RelationshipOperationsTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());
    private AdminRelationshipOperations relationshipOperations;

    @Before
    public void init() {
        relationshipOperations = new AdminRelationshipTemplate(new RestClient(createRestTemplate(), baseURI(wireMockRule.port(), "v1", "relationships")), true);
    }

    @Test
    public void test_getPartnerRelationships_OK() throws Exception {
        given_getPartnerRelationships_200_OK(PartnerRelationshipType.IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF);

        final PartnerCenterResponse<PartnerRelationship> relationships = relationshipOperations.getPartnerRelationships(PartnerRelationshipType.IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF)
                .getBody();


        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(relationships.getItems()).allMatch(relationship -> relationship.getRelationshipType() == PartnerRelationshipType.IS_INDIRECT_CLOUD_SOLUTION_PROVIDER_OF);
        });
    }
}
