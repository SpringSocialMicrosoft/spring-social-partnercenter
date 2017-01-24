package org.springframework.social.partnercenter.api.billing.usage;

import java.time.ZonedDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface UsageOperations {

	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, Granularity granularity);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, Granularity granularity, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, Granularity granularity, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, Granularity granularity, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, ZonedDateTime startDateTime, ZonedDateTime endDateTime, int size);
	ResponseEntity<CustomerUsageSummary> getUsageSummary(String customerId);
	ResponseEntity<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);
}

