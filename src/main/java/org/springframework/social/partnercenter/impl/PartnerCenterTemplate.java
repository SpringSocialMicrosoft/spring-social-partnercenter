package org.springframework.social.partnercenter.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.operations.CustomerOperations;
import org.springframework.social.partnercenter.operations.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.operations.SubscriptionOperations;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.uri.UriProvider;

public class PartnerCenterTemplate  extends AbstractOAuth2ApiBinding implements PartnerCenter {
	private final SubscriptionOperations subscriptionOperations;
	private final OrderOperations orderOperations;
	private final CustomerOperations customerOperations;

	public PartnerCenterTemplate(String accessToken, String version) {
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()), isAuthorized());
		orderOperations = new OrderTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()), isAuthorized());
		customerOperations = new CustomerTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()) ,isAuthorized());

	}

	@Override
	public SubscriptionOperations getSubscriptionOperations() {
		return subscriptionOperations;
	}

	@Override
	public OrderOperations getOrderOperations() {
		return orderOperations;
	}

	@Override
	public CustomerOperations getCustomerOperations() {
		return customerOperations;
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
