package org.springframework.social.partnercenter.api.billing.usage;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory.createRestTemplate;
import static org.springframework.social.partnercenter.test.stubs.UsageOperationStubs.given_getUtilizationRecords_200_OK;
import static org.springframework.social.partnercenter.test.utils.UUIDUtils.createUUIDAsString;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.usage.impl.UsageTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.Resource;
import org.springframework.social.partnercenter.test.stubs.StubURI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class UsageOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());
	private UsageOperations usageOperations;

	@Before
	public void init() {
		usageOperations = new UsageTemplate(UriProvider.DEFAULT_URL_PROVIDER, new RestClient(createRestTemplate(), StubURI.customersBase(wireMockRule.port())), true);
	}

	@Test
	public void test() {
		final String customerId = createUUIDAsString(1);
		final String subscriptionId = createUUIDAsString(2);
		given_getUtilizationRecords_200_OK(customerId, subscriptionId);

		final ZonedDateTime startTime = ZonedDateTime.of(2018, 3, 25, 0, 0, 0, 0, ZoneId.of("UTC"));
		final ZonedDateTime endTime = ZonedDateTime.of(2018, 3, 26, 0, 0, 0, 0, ZoneId.of("UTC"));
		final List<UtilizationRecord> utilizationRecords = usageOperations.getUtilizationRecords(customerId, subscriptionId,
				startTime.toInstant(),
				endTime.toInstant())
				.getBody().getItems();

		final List<UtilizationRecord> expectedRecords = Resource.parseFile("data/usage/get_utilization_OK.json").getJsonAsObject(new TypeReference<PartnerCenterResponse<UtilizationRecord>>() {
		}).getItems();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(utilizationRecords).extracting(UtilizationRecord::getQuantity).containsAll(expectedRecords.stream().map(UtilizationRecord::getQuantity).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(UtilizationRecord::getUnit).containsAll(expectedRecords.stream().map(UtilizationRecord::getUnit).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getResource().getCategory()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getResource().getCategory()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getResource().getId()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getResource().getId()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getResource().getName()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getResource().getName()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getResource().getSubcategory()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getResource().getSubcategory()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredRegion()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredRegion()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredService()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredService()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredServiceType()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getInfoFields().getMeteredServiceType()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(utilizationRecord -> utilizationRecord.getInfoFields().getProject()).containsAll(expectedRecords.stream().map(utilizationRecord -> utilizationRecord.getInfoFields().getProject()).collect(toList()));
			softly.assertThat(utilizationRecords).extracting(UtilizationRecord::getUsageEndTime).containsAll(expectedRecords.stream().map(UtilizationRecord::getUsageEndTime).collect(toList()));
			softly.assertThat(utilizationRecords.get(0).getUsageStartTime()).isEqualTo(startTime);
			softly.assertThat(utilizationRecords.get(0).getUsageEndTime()).isEqualTo(endTime);

		});
		assertThat(utilizationRecords).extracting(UtilizationRecord::getQuantity)
				.containsAll(expectedRecords.stream().map(UtilizationRecord::getQuantity).collect(toList()));
	}
}