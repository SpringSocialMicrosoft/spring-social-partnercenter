package org.springframework.social.partnercenter.api.billing.usage;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface UsageOperations extends PagingResourceOperations<UtilizationRecord>{

	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Boolean showDetails, Integer size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Integer size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Boolean showDetails, Integer size);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Boolean showDetails);
	ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Integer size);
	ResponseEntity<CustomerUsageSummary> getUsageSummary(String customerId);

	ResponseEntity<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId);

	/**
	 * Retrieves the usage summary of a subscription
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @param subscriptionId The value is a GUID formatted subscription-id that identifies the subscription for which usage records are required.
	 * @return {@link SubscriptionUsageSummary}
	 */
	ResponseEntity<SubscriptionUsageSummary> getSubscriptionUsageSummary(String customerId, String subscriptionId);

	/**
	 * Retrieves the daily usage records of a subscription.
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @param subscriptionId The value is a GUID formatted subscription-id that identifies the subscription for which usage records are required.
	 * @return {@link SubscriptionDailyUsage}
	 */
	ResponseEntity<PartnerCenterResponse<SubscriptionDailyUsage>> getDailySubscriptionUsage(String customerId, String subscriptionId);

	/**
	 * Retrieves the monthly usage records for all the subscriptions of a given customer.
	 * @param customerId The value is a GUID formatted customer-id that identifies the customer.
	 * @return {@link SubscriptionMonthlyUsageRecord}
	 */
	ResponseEntity<PartnerCenterResponse<SubscriptionMonthlyUsageRecord>> getMonthlyUsageForSubscriptions(String customerId);

	/**
	 * Retrieves the usage summary of a partner.
	 * @return {@link PartnerUsageSummary}
	 */
	ResponseEntity<PartnerUsageSummary> getPartnerUsage();
}

