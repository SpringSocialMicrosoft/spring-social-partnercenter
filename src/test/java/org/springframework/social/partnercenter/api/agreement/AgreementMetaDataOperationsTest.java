package org.springframework.social.partnercenter.api.agreement;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.social.partnercenter.api.agreement.AgreementType.MICROSOFT_CLOUD_AGREEMENT;
import static org.springframework.social.partnercenter.test.stubs.AgreementMetaDataOperationStubs.given_getAgreementMetadata_200_OK;
import static org.springframework.social.partnercenter.test.stubs.StubURI.baseURI;
import static org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory.createRestTemplate;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.http.client.RestClient;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AgreementMetaDataOperationsTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());

	@Test
	public void getAgreementMetadatas_whenCalled_thenResponseIsParsedCorrectly() {
		given_getAgreementMetadata_200_OK();

		AgreementMetaDataOperations agreementMetaDataOperations = new AgreementMetaDataTemplate(new RestClient(createRestTemplate(),
		                                                                                                       baseURI(wireMockRule.port(), "v1")),
		                                                                                        true);

		PartnerCenterResponse<AgreementMetaData> res = agreementMetaDataOperations.getAgreementMetadatas().getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(res.getItems()).hasSize(1);

			AgreementMetaData agreementMetaData = res.getItems().get(0);
			softly.assertThat(agreementMetaData.getTemplateId()).isEqualTo("00001111-2222-3333-4444-555566667777");
			softly.assertThat(agreementMetaData.getAgreementType()).isEqualTo(MICROSOFT_CLOUD_AGREEMENT);
			softly.assertThat(agreementMetaData.getAgreementLink()).isEqualTo("https://domain.com");
			softly.assertThat(agreementMetaData.getVersionRank()).isEqualTo(0);
		});
	}
}
