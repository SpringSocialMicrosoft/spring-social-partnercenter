package org.springframework.social.partnercenter.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.api.customer.impl.CustomerTemplate;
import org.springframework.social.partnercenter.api.order.OfferOperations;
import org.springframework.social.partnercenter.api.order.impl.OfferTemplate;
import org.springframework.social.partnercenter.api.order.impl.OrderTemplate;
import org.springframework.social.partnercenter.api.order.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.api.order.SubscriptionOperations;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.api.uri.UriProvider;

public class PartnerCenterTemplate  extends AbstractOAuth2ApiBinding implements PartnerCenter {
	private final SubscriptionOperations subscriptionOperations;
	private final OrderOperations orderOperations;
	private final CustomerOperations customerOperations;
	private final OfferOperations offerOperations;

	public PartnerCenterTemplate(String accessToken, String version) {
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()), isAuthorized());
		orderOperations = new OrderTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()), isAuthorized());
		customerOperations = new CustomerTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerApiBuilder().toUriString()) ,isAuthorized());
		offerOperations = new OfferTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterBuilder().pathSegment("v1", "offer").toUriString()), isAuthorized());

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

	@Override
	public OfferOperations getOfferOperations() {
		return offerOperations;
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
