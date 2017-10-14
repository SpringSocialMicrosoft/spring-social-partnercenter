package org.springframework.social.partnercenter.api.analytics.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.analytics.AnalyticsOperations;
import org.springframework.social.partnercenter.api.analytics.CustomerLicensesDeploymentInsights;
import org.springframework.social.partnercenter.api.analytics.CustomerLicensesUsageInsights;
import org.springframework.social.partnercenter.api.analytics.PartnerLicensesDeploymentInsights;
import org.springframework.social.partnercenter.api.analytics.PartnerLicensesUsageInsights;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AnalyticsTemplate extends AbstractTemplate implements AnalyticsOperations{
	private RestResource restResource;

	public AnalyticsTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<PartnerLicensesDeploymentInsights>> getPartnerLicensesDeploymentInsights() {
		return restResource.request()
				.pathSegment("analytics", "licenses", "deployment")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<PartnerLicensesDeploymentInsights>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<PartnerLicensesUsageInsights>> getPartnerLicensesUsageInsights() {
		return restResource.request()
				.pathSegment("analytics", "licenses", "usage")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<PartnerLicensesUsageInsights>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<CustomerLicensesDeploymentInsights>> getCustomerLicensesDeploymentInsights(String customerId) {
		return restResource.request()
				.pathSegment("customers", customerId, "analytics", "licenses", "deployment")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<CustomerLicensesDeploymentInsights>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<CustomerLicensesUsageInsights>> getCustomerLicensesUsageInsights(String customerId) {
		return restResource.request()
				.pathSegment("customers", customerId, "analytics", "licenses", "usage")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<CustomerLicensesUsageInsights>>() {});
	}

	@Override
	protected String getProviderId() {
		return null;
	}
}
