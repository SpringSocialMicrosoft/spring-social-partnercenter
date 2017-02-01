package org.springframework.social.partnercenter.api.billing.usage;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface UsageOperations extends PagingResourceOperations<UtilizationRecord>{

	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, int size);
	ResponseEntity<CustomerUsageSummary> getUsageSummary(String customerId);
	ResponseEntity<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);
}

