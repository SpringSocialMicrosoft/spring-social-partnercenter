package org.springframework.social.partnercenter.api.billing.usage.impl;

import static org.springframework.social.partnercenter.api.billing.usage.UtilizationRecord.Granularity.DAILY;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.usage.AzureResourceMonthlyUsageRecord;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.billing.usage.CustomerUsageSummary;
import org.springframework.social.partnercenter.api.billing.usage.UtilizationRecord;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UsageTemplate extends AbstractTemplate implements UsageOperations {
	public static final String SUBSCRIPTIONS = "subscriptions";
	private RestResource restResource;

	public UsageTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, true, 1000);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, true, 1000);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, showDetails, 1000);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails, int size) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "utilizations", "azure")
				.queryParam("start_time", startDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
				.queryParam("end_time", endDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
				.queryParam("granularity", granularity.value())
				.queryParam("show_details", showDetails)
				.queryParam("size", size)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<UtilizationRecord>>() {});
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, int size) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, true, size);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails, int size) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, showDetails, size);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, showDetails, 1000);
	}

	@Override
	public PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, int size) {
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, true, size);
	}

	@Override
	public CustomerUsageSummary getUsageSummary(String customerId) {
		return restResource.request()
				.pathSegment(customerId, "usagesummary")
				.get(CustomerUsageSummary.class);
	}

	@Override
	public PartnerCenterResponse<AzureResourceMonthlyUsageRecord> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId) {
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "usagerecords", "resources")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
