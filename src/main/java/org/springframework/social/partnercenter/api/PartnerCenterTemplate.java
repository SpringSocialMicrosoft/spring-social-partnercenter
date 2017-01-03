package org.springframework.social.partnercenter.api;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.billing.invoicing.InvoiceOperations;
import org.springframework.social.partnercenter.api.billing.invoicing.impl.InvoiceTemplate;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.billing.usage.impl.UsageTemplate;
import org.springframework.social.partnercenter.api.billing.pricing.PricingOperations;
import org.springframework.social.partnercenter.api.billing.pricing.impl.PricingTemplate;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.CustomerTemplate;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.order.offer.impl.OfferTemplate;
import org.springframework.social.partnercenter.api.order.impl.OrderTemplate;
import org.springframework.social.partnercenter.api.order.subscription.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PartnerCenterTemplate  extends AbstractOAuth2ApiBinding implements PartnerCenter {
	private final SubscriptionOperations subscriptionOperations;
	private final OrderOperations orderOperations;
	private final CustomerOperations customerOperations;
	private final OfferOperations offerOperations;
	private final UsageOperations usageOperations;
	private final PricingOperations pricingOperations;
	private final InvoiceOperations invoiceOperations;

	private PartnerCenterTemplate(String accessToken, String version) {
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		orderOperations = new OrderTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		customerOperations = new CustomerTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerUri().toUriString()) ,isAuthorized());

		offerOperations = new OfferTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterOfferUri().toUriString()), isAuthorized());

		usageOperations = new UsageTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		pricingOperations = new PricingTemplate(new RestResource(getRestTemplate(),
				UriProvider.partnerCenterPricingUri().toUriString()), isAuthorized());

		invoiceOperations = new InvoiceTemplate(new RestResource(getRestTemplate(),
						UriProvider.partnerCenterInvoiceUri().toUriString()), isAuthorized());

	}
	public PartnerCenterTemplate(String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(accessToken, version);
		interceptors.forEach(interceptor -> getRestTemplate().getInterceptors().add(interceptor));
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

	public UsageOperations getUsageOperations() {
		return usageOperations;
	}

	@Override
	public PricingOperations getPricingOperations() {
		return pricingOperations;
	}

	@Override
	public InvoiceOperations getInvoiceOperations() {
		return invoiceOperations;
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
