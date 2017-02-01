package org.springframework.social.partnercenter.api.billing.usage;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface UsageOperations extends PagingResourceOperations<UtilizationRecord>{

	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, Granularity granularity);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, Granularity granularity, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, Granularity granularity, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, Granularity granularity, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean showDetails, int size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, LocalDateTime startDateTime, LocalDateTime endDateTime, int size);
	ResponseEntity<CustomerUsageSummary> getUsageSummary(String customerId);
	ResponseEntity<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);
}

