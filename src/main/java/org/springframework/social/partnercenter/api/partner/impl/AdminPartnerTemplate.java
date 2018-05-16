package org.springframework.social.partnercenter.api.partner.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.query.Filter;
import org.springframework.social.partnercenter.api.customer.query.Operator;
import org.springframework.social.partnercenter.api.order.subscription.Subscription;
import org.springframework.social.partnercenter.api.partner.AdminPartnerOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AdminPartnerTemplate extends AbstractTemplate implements AdminPartnerOperations{
	private static final String CUSTOMERS = "customers";
	private final RestResource restResource;

	public AdminPartnerTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Subscription>> getSubscriptionsByPartner(String customerId, String mpnId, int offset, int size) {
		return restResource.request()
				.pathSegment(CUSTOMERS, customerId)
				.queryParam("mpn_id", mpnId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Subscription>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Customer>> getCustomersOfAnIndirectReseller(String resellerID, int offset, int size) {
		return restResource.request()
				.pathSegment(CUSTOMERS)
				.queryParam("size", size)
				.queryParam("filter", Filter.builder().operator(Operator.STARTS_WITH).field("IndirectReseller"))
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Customer>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
