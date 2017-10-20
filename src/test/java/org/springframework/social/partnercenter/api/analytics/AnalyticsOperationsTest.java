package org.springframework.social.partnercenter.api.analytics;

import static org.springframework.social.partnercenter.test.stubs.AnalyticsOperationStubs.given_getPartnerLicensesDeploymentInsights_200_OK;
import static org.springframework.social.partnercenter.test.stubs.AnalyticsOperationStubs.given_getPartnerLicensesUsageInsights_200_OK;
import static org.springframework.social.partnercenter.test.stubs.TestURIs.baseURI;

import java.time.ZonedDateTime;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.analytics.impl.AnalyticsTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AnalyticsOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	@Test
	public void testGetPartnerLicensesDeploymentInsights() {
		given_getPartnerLicensesDeploymentInsights_200_OK();

		final AnalyticsOperations analyticsOperations = new AnalyticsTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), baseURI("v1")), true);
		final PartnerCenterResponse<PartnerLicensesDeploymentInsights> insightsPartnerCenterResponse = analyticsOperations.getPartnerLicensesDeploymentInsights().getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getChannel()).isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getServiceName()).isEqualTo("crm");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getLicensesSold()).isEqualTo(206.0);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getProratedDeploymentPercent()).isEqualTo(2.43);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getProcessedDateTime()).isEqualTo(ZonedDateTime.parse("2017-10-09T00:00:00Z"));
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getAttributes().getObjectType()).isEqualTo("PartnerLicensesDeploymentInsights");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getChannel()).isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getServiceName()).isEqualTo("o365");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getLicensesSold()).isEqualTo(474.0);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getProratedDeploymentPercent()).isEqualTo(26.16);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getProcessedDateTime()).isEqualTo(ZonedDateTime.parse("2017-10-13T03:55:46.21Z"));
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getAttributes().getObjectType()).isEqualTo("PartnerLicensesDeploymentInsights");
		});
	}

	@Test
	public void testGetPartnerLicensesUsageInsights() {
		given_getPartnerLicensesUsageInsights_200_OK();

		final AnalyticsOperations analyticsOperations = new AnalyticsTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), baseURI("v1")), true);
		final PartnerCenterResponse<PartnerLicensesUsageInsights> insightsPartnerCenterResponse = analyticsOperations.getPartnerLicensesUsageInsights().getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getChannel()).as("Channel").isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getProratedLicensesUsagePercent()).as("Percent").isEqualTo(0.0);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getServiceName()).as("Service Name").isEqualTo("crm");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getWorkloadName()).as("Workload Name").isEqualTo("Microsoft Dynamics CRM");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(0).getProcessedDateTime()).as("Processed Date Time").isEqualTo(ZonedDateTime.parse("2017-10-09T00:00:00Z"));

			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getChannel()).as("Channel").isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getProratedLicensesUsagePercent()).as("Percent").isEqualTo(2.13);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getServiceName()).as("Service Name").isEqualTo("o365");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getWorkloadName()).as("Workload Name").isEqualTo("Exchange");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(1).getProcessedDateTime()).as("Processed Date Time").isEqualTo(ZonedDateTime.parse("2017-10-08T00:00:00Z"));

			softly.assertThat(insightsPartnerCenterResponse.getItems().get(2).getChannel()).as("Channel").isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(2).getProratedLicensesUsagePercent()).as("Percent").isEqualTo(3.14);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(2).getServiceName()).as("Service Name").isEqualTo("o365");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(2).getWorkloadName()).as("Workload Name").isEqualTo("SharePoint");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(2).getProcessedDateTime()).as("Processed Date Time").isEqualTo(ZonedDateTime.parse("2017-10-08T00:00:00Z"));

			softly.assertThat(insightsPartnerCenterResponse.getItems().get(3).getChannel()).as("Channel").isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(3).getProratedLicensesUsagePercent()).as("Percent").isEqualTo(5.64);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(3).getServiceName()).as("Service Name").isEqualTo("o365");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(3).getWorkloadName()).as("Workload Name").isEqualTo("Skype For Business");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(3).getProcessedDateTime()).as("Processed Date Time").isEqualTo(ZonedDateTime.parse("2017-10-08T00:00:00Z"));

			softly.assertThat(insightsPartnerCenterResponse.getItems().get(4).getChannel()).as("Channel").isEqualTo("reseller");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(4).getProratedLicensesUsagePercent()).as("Percent").isEqualTo(0.1);
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(4).getServiceName()).as("Service Name").isEqualTo("o365");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(4).getWorkloadName()).as("Workload Name").isEqualTo("Teams");
			softly.assertThat(insightsPartnerCenterResponse.getItems().get(4).getProcessedDateTime()).as("Processed Date Time").isEqualTo(ZonedDateTime.parse("2017-10-08T00:00:00Z"));
		});
	}
}
