package org.springframework.social.partnercenter.api.audit;

import static org.springframework.social.partnercenter.test.stubs.AuditOperationsStubs.given_getAuditRecord_200_OK;
import static org.springframework.social.partnercenter.test.stubs.TestURIs.baseURI;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.impl.AuditTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.Resource;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AuditOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	@Test
	public void testGetAuditRecord_validateResponseParsedCorrectly() {
		given_getAuditRecord_200_OK();
		final PartnerCenterResponse<AuditRecord> expectedRecords = Resource.parseFile("data/audit/record.json").getJsonAsObject(new TypeReference<PartnerCenterResponse<AuditRecord>>(){});
		final AuditOperations auditOperations = new AuditTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), baseURI("v1/auditrecords")));
		final PartnerCenterResponse<AuditRecord> records = auditOperations.getPartnerCenterActivity(Instant.now().minus(100, ChronoUnit.DAYS)).getBody();

		SoftAssertions.assertSoftly(softly -> {
			for (int i = 0; i < records.getItems().size(); i++) {
				softly.assertThat(records.getItems().get(i).getPartnerId()).as("Partner ID").isEqualTo(expectedRecords.getItems().get(i).getPartnerId());
				softly.assertThat(records.getItems().get(i).getApplicationId()).as("Application ID").isEqualTo(expectedRecords.getItems().get(i).getApplicationId());
				softly.assertThat(records.getItems().get(i).getCustomerId()).as("Customer ID").isEqualTo(expectedRecords.getItems().get(i).getCustomerId());
				softly.assertThat(records.getItems().get(i).getOperationDate()).as("Operation Date").isEqualTo(expectedRecords.getItems().get(i).getOperationDate());
				softly.assertThat(records.getItems().get(i).getOperationStatus()).as("Operation Status").isEqualTo(expectedRecords.getItems().get(i).getOperationStatus());
				softly.assertThat(records.getItems().get(i).getOperationType()).as("Operation Type").isEqualTo(expectedRecords.getItems().get(i).getOperationType());
				softly.assertThat(records.getItems().get(i).getCustomerName()).as("Customer Name").isEqualTo(expectedRecords.getItems().get(i).getCustomerName());
				softly.assertThat(records.getItems().get(i).getUserPrincipalName()).as("User Principal Name").isEqualTo(expectedRecords.getItems().get(i).getUserPrincipalName());
				softly.assertThat(records.getItems().get(i).getResourceNewValue()).as("Resource New Value").isEqualTo(expectedRecords.getItems().get(i).getResourceNewValue());
				softly.assertThat(records.getItems().get(i).getResourceType()).as("Resource Type").isEqualTo(expectedRecords.getItems().get(i).getResourceType());
			}
		});
	}
}
