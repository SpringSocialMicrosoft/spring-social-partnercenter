package org.springframework.social.partnercenter.api.analytics;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AnalyticsOperations {
	/**
	 * Get partner licenses deployment information aggregated to include all customers
	 *
	 * @return {@link PartnerCenterResponse<PartnerLicensesDeploymentInsights>}
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt797761.aspx">MSDN: Get partner licenses deployment information</a>
	 */
	ResponseEntity<PartnerCenterResponse<PartnerLicensesDeploymentInsights>> getPartnerLicensesDeploymentInsights();

	/**
	 * Get partner licenses usage information aggregated to include all customers.
	 *
	 * @return {@link PartnerCenterResponse<PartnerLicensesUsageInsights>}
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt797762.aspx">MSDN: Get partner licenses usage information</a>
	 */
	ResponseEntity<PartnerCenterResponse<PartnerLicensesUsageInsights>> getPartnerLicensesUsageInsights();

	/**
	 * Get licenses deployment insights for a specific customer.
	 *
	 * @param customerId A GUID formatted customer-id that identifies the customer.
	 * @return {@link PartnerCenterResponse<CustomerLicensesDeploymentInsights>}
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt797759.aspx">MSDN: Get customer licenses usage information</a>
	 */
	ResponseEntity<PartnerCenterResponse<CustomerLicensesDeploymentInsights>> getCustomerLicensesDeploymentInsights(String customerId);

	/**
	 * Get licenses deployment insights for a specific customer.
	 *
	 * @param customerId A GUID formatted customer-id that identifies the customer.
	 * @return {@link PartnerCenterResponse<CustomerLicensesUsageInsights>}
	 * @see <a href="https://msdn.microsoft.com/en-us/library/partnercenter/mt797760.aspx">MSDN: Get customer licenses usage information</a>
	 */
	ResponseEntity<PartnerCenterResponse<CustomerLicensesUsageInsights>> getCustomerLicensesUsageInsights(String customerId);
}
