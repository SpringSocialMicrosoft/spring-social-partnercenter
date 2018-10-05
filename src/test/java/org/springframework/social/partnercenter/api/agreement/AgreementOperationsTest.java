package org.springframework.social.partnercenter.api.agreement;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.time.Instant.now;
import static java.time.Instant.parse;
import static org.springframework.social.partnercenter.api.agreement.AgreementType.MICROSOFT_CLOUD_AGREEMENT;
import static org.springframework.social.partnercenter.test.stubs.AgreementOperationStubs.given_confirmCustomerAcceptance_201_Created;
import static org.springframework.social.partnercenter.test.stubs.AgreementOperationStubs.given_getAgreementMetadata_200_OK;
import static org.springframework.social.partnercenter.test.stubs.AgreementOperationStubs.given_getConfirmations_200_OK;
import static org.springframework.social.partnercenter.test.stubs.StubURI.baseURI;
import static org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory.createRestTemplate;
import static org.springframework.social.partnercenter.test.utils.UUIDUtils.createUUIDAsString;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.profile.Contact;
import org.springframework.social.partnercenter.http.client.RestClient;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AgreementOperationsTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());

	@Test
	public void getAgreementMetadatas_whenCalled_thenResponseIsParsedCorrectly() {
		given_getAgreementMetadata_200_OK();

		AgreementOperations agreementOperations = new AgreementTemplate(new RestClient(createRestTemplate(),
		                                                                               baseURI(wireMockRule.port(), "v1")),
		                                                                true);

		PartnerCenterResponse<AgreementMetaData> res = agreementOperations.getAgreementMetadatas().getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(res.getItems()).hasSize(1);

			AgreementMetaData agreementMetaData = res.getItems().get(0);
			softly.assertThat(agreementMetaData.getTemplateId()).isEqualTo("00001111-2222-3333-4444-555566667777");
			softly.assertThat(agreementMetaData.getAgreementType()).isEqualTo(MICROSOFT_CLOUD_AGREEMENT);
			softly.assertThat(agreementMetaData.getAgreementLink()).isEqualTo("https://domain.com");
			softly.assertThat(agreementMetaData.getVersionRank()).isEqualTo(0);
		});
	}

	@Test
	public void confirmCustomerAcceptance_whenCalled_thenResponseIsParsedCorrectly() {
		Agreement givenAgreement = anAgreement();
		given_confirmCustomerAcceptance_201_Created(givenAgreement);

		AgreementOperations agreementOperations = new AgreementTemplate(new RestClient(createRestTemplate(),
		                                                                               baseURI(wireMockRule.port(), "v1")),
		                                                                true);

		Agreement agreement = agreementOperations.confirmCustomerAcceptance("tenantId", givenAgreement).getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(agreement.getUserId()).isEqualTo(givenAgreement.getUserId());
			softly.assertThat(agreement.getPrimaryContact()).isEqualToComparingFieldByFieldRecursively(givenAgreement.getPrimaryContact());
			softly.assertThat(agreement.getDateAgreed()).isEqualTo(givenAgreement.getDateAgreed());
			softly.assertThat(agreement.getTemplateId()).isEqualTo(givenAgreement.getTemplateId());
			softly.assertThat(agreement.getType()).isEqualTo(givenAgreement.getType());
			softly.assertThat(agreement.getAgreementLink()).isEqualTo(givenAgreement.getAgreementLink());
		});
	}

	@Test
	public void getConfirmations_whenCalled_thenResponseIsParsedCorrectly() {
		given_getConfirmations_200_OK();

		AgreementOperations agreementOperations = new AgreementTemplate(new RestClient(createRestTemplate(),
		                                                                               baseURI(wireMockRule.port(), "v1")),
		                                                                true);

		PartnerCenterResponse<Agreement> res = agreementOperations.getConfirmations("tenantId").getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(res.getItems()).hasSize(1);

			Agreement agreement = res.getItems().get(0);
			softly.assertThat(agreement.getTemplateId()).isEqualTo("00001111-2222-3333-4444-555566667777");
			softly.assertThat(agreement.getDateAgreed()).isEqualTo(parse("2018-10-01T00:00:00.123Z"));
			softly.assertThat(agreement.getType()).isEqualTo(MICROSOFT_CLOUD_AGREEMENT);
			softly.assertThat(agreement.getAgreementLink()).isEqualTo("https://domain.com");

			Contact primaryContact = agreement.getPrimaryContact();
			softly.assertThat(primaryContact).isNotNull();
			softly.assertThat(primaryContact.getFirstName()).isEqualTo("John");
			softly.assertThat(primaryContact.getLastName()).isEqualTo("Doe");
			softly.assertThat(primaryContact.getEmail()).isEqualTo("john.doe@domain.com");
			softly.assertThat(primaryContact.getPhoneNumber()).isEqualTo("1234567890");
		});
	}

	private Agreement anAgreement() {
		Agreement agreement = new Agreement();
		agreement.setUserId(createUUIDAsString(1));
		agreement.setDateAgreed(now());
		agreement.setTemplateId(createUUIDAsString(2));
		agreement.setType(MICROSOFT_CLOUD_AGREEMENT);

		Contact contact = new Contact();
		contact.setFirstName("John");
		contact.setLastName("Doe");
		contact.setEmail("john.doe@domain.com");
		contact.setPhoneNumber("5551234567");
		agreement.setPrimaryContact(contact);

		return agreement;
	}
}
