package org.springframework.social.partnercenter.api.billing.usage;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface UsageOperations {

	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails, int size);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, int size);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails, int size);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails);
	PartnerCenterResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, int size);
	CustomerUsageSummary getUsageSummary(String customerId);
	PartnerCenterResponse<AzureResourceMonthlyUsageRecord> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);
}

