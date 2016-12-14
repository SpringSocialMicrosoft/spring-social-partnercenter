package org.springframework.social.partnercenter.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.operations.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestService;
import org.springframework.social.partnercenter.operations.SubscriptionOperations;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;

public class PartnerCenterTemplate  extends AbstractOAuth2ApiBinding implements PartnerCenter {
	private final SubscriptionOperations subscriptionOperations;
	private final OrderOperations orderOperations;

	public PartnerCenterTemplate(String accessToken, String version) {
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(new RestService(getRestTemplate()), isAuthorized());
		orderOperations = new OrderTemplate(new RestService(getRestTemplate()), isAuthorized());
	}

	@Override
	public SubscriptionOperations getSubscriptionOperations() {
		return subscriptionOperations;
	}

	@Override
	public OrderOperations getOrderOperations() {
		return orderOperations;
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
