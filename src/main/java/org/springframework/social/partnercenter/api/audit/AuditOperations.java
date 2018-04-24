package org.springframework.social.partnercenter.api.audit;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AuditOperations {

	/**
	 * Retrieves audit records for a date range specified by the start date.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days.
	 * Requests with a start date greater than 90 days prior to the current date will receive a bad request exception (error code: 400)
	 * and an appropriate message.
	 *
	 * @param startDate Beginning of audit period
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate);

	/**
	 * Retrieves audit records for a date range specified by including the start date and the end date.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days.
	 * Requests with a start date greater than 90 days prior to the current date will receive a bad request exception (error code: 400)
	 * and an appropriate message.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivity(Instant startDate, Instant endDate);

	/**
	 * Retrieves audit records for a date range specified by including the start date and the end date for the Company passed in the 'companyName' parameter.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days.
	 * Requests with a start date greater than 90 days prior to the current date will receive a bad request exception (error code: 400)
	 * and an appropriate message.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @param companyName records can be filtered by 'CompanyName', 'CustomerId' or 'ResourceType
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCompanyName(Instant startDate, Instant endDate, String companyName);

	/**
	 * Retrieves audit records for a date range specified by including the start date and the end date for the Customer passed in the 'customerId' parameter.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days.
	 * Requests with a start date greater than 90 days prior to the current date will receive a bad request exception (error code: 400)
	 * and an appropriate message.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @param customerId records are filtered by 'customerId'
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByCustomerId(Instant startDate, Instant endDate, String customerId);

	/**
	 * Retrieves audit records for a date range specified by including the start date and the end date for the ResourceType passed in the 'resourceType' parameter.
	 * Note, however, that for performance reasons activity log data availability is limited to the previous 90 days.
	 * Requests with a start date greater than 90 days prior to the current date will receive a bad request exception (error code: 400)
	 * and an appropriate message.
	 *
	 * @param startDate Beginning of audit period
	 * @param endDate End of audit Period
	 * @param resourceType records are filtered by 'resourceType'
	 * @return list of activity
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt725332.aspx">MSDN: Get a record of Partner Center activity</a>
	 */
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getPartnerCenterActivityByResourceType(Instant startDate, Instant endDate, ResourceType resourceType);
}
