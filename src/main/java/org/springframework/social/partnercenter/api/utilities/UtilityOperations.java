package org.springframework.social.partnercenter.api.utilities;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.AuditRecord;
import org.springframework.social.partnercenter.api.customer.Address;
import org.springframework.social.partnercenter.api.customer.query.Filter;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	ResponseEntity<Boolean> validateAddress(Address address);
	ResponseEntity deleteCustomer(String customerId);

	/**
	 * Retrieves audit records for the previous 30 days from the current date, or for a date range specified by including the start date and/or the end date.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days. Requests with a start date greater than 90 days
	 * prior to the current date will receive a bad request exception (error code: 400) and an appropriate message.
	 *
	 * This has been replace by {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByCompanyName(Instant, Instant, String)},
	 * {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByCustomerId(Instant, Instant, String)} (Instant, Instant, String)} or
	 * {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByResourceType(Instant, Instant, org.springframework.social.partnercenter.api.audit.ResourceType)} (Instant, Instant, String)}.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @param filter records can be filtered by 'CompanyName', 'CustomerId' or 'ResourceType
	 * @return list of activity
	 * @deprecated This method will be removed in release 7.0.0
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	@Deprecated
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate, Filter filter);

	/**
	 * Retrieves audit records for the previous 30 days from the current date, or for a date range specified by including the start date and/or the end date.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days. Requests with a start date greater than 90 days
	 * prior to the current date will receive a bad request exception (error code: 400) and an appropriate message.
	 *
	 * This has been replace by {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByCompanyName(Instant, Instant, String)},
	 * {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByCustomerId(Instant, Instant, String)} (Instant, Instant, String)} or
	 * {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivityByResourceType(Instant, Instant, org.springframework.social.partnercenter.api.audit.ResourceType)} (Instant, Instant, String)}.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @param filter records can be filtered by 'CompanyName', 'CustomerId' or 'ResourceType
	 * @return list of activity
	 * @deprecated This method will be removed in release 7.0.0
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	@Deprecated
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate, String filter);

	/**
	 * Retrieves audit records for the previous 30 days from the current date, or for a date range specified by including the start date and/or the end date.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days. Requests with a start date greater than 90 days
	 * prior to the current date will receive a bad request exception (error code: 400) and an appropriate message.
	 *
	 * This has been replace by {@link org.springframework.social.partnercenter.api.audit.AuditOperations#getPartnerCenterActivity(Instant, Instant)}
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	@Deprecated
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate);
}
