package org.springframework.social.partnercenter.api.analytics;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AnalyticsOperations {
	ResponseEntity<PartnerCenterResponse<PartnerLicensesDeploymentInsights>> getPartnerLicensesDeploymentInsights();
	ResponseEntity<PartnerCenterResponse<PartnerLicensesUsageInsights>> getPartnerLicensesUsageInsights();
	ResponseEntity<PartnerCenterResponse<CustomerLicensesDeploymentInsights>> getCustomerLicensesDeploymentInsights(String customerId);
	ResponseEntity<PartnerCenterResponse<CustomerLicensesUsageInsights>> getCustomerLicensesUsageInsights(String customerId);
}
