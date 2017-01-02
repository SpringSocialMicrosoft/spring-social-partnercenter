package org.springframework.social.partnercenter.api.billing.usage;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.PartnerCenterPaginatedResponse;

public interface UsageOperations {

	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, boolean showDetails, int size);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, UtilizationRecord.Granularity granularity, int size);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails, int size);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails);
	PartnerCenterPaginatedResponse<UtilizationRecord> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, int size);
	CustomerUsageSummary getUsageSummary(String customerId);
	PartnerCenterPaginatedResponse<AzureResourceMonthlyUsageRecord> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);
}

