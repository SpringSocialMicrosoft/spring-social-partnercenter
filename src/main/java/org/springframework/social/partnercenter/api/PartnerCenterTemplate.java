package org.springframework.social.partnercenter.api;

import static org.springframework.social.partnercenter.http.logging.HttpRequestResponseLoggerFactory.createSlf4jApiLogger;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.billing.invoicing.InvoiceOperations;
import org.springframework.social.partnercenter.api.billing.invoicing.impl.InvoiceTemplate;
import org.springframework.social.partnercenter.api.billing.pricing.PricingOperations;
import org.springframework.social.partnercenter.api.billing.pricing.impl.PricingTemplate;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.billing.usage.impl.UsageTemplate;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.CustomerTemplate;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.api.customer.user.impl.UserTemplate;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.impl.OrderTemplate;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.offer.impl.OfferTemplate;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.api.profile.ProfileOperations;
import org.springframework.social.partnercenter.api.profile.impl.ProfileTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.api.utilities.UtilityOperations;
import org.springframework.social.partnercenter.api.utilities.impl.UtilityTemplate;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.http.logging.LoggingRequestInterceptor;
import org.springframework.web.client.RestTemplate;

public class PartnerCenterTemplate extends AbstractOAuth2ApiBinding implements PartnerCenter {
	private final UriProvider uriProvider;
	private final SubscriptionOperations subscriptionOperations;
	private final OrderOperations orderOperations;
	private final CustomerOperations customerOperations;
	private final OfferOperations offerOperations;
	private final UsageOperations usageOperations;
	private final PricingOperations pricingOperations;
	private final InvoiceOperations invoiceOperations;
	private final ProfileOperations profileOperations;
	private final UtilityOperations utilityOperations;
	private final UserOperations userOperations;

	public PartnerCenterTemplate(UriProvider uriProvider, String accessToken, String version) {
		super(accessToken);
		addVersionInterceptor(version);
		this.uriProvider = uriProvider;
		subscriptionOperations = new SubscriptionTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().toUriString()),
				isAuthorized());

		orderOperations = new OrderTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		customerOperations = new CustomerTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		offerOperations = new OfferTemplate(createRestResource(
				uriProvider.partnerCenterOfferUri().toUriString()), isAuthorized());

		usageOperations = new UsageTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());

		pricingOperations = new PricingTemplate(createRestResource(
				uriProvider.partnerCenterPricingUri().toUriString()), isAuthorized());

		invoiceOperations = new InvoiceTemplate(createRestResource(
				uriProvider.partnerCenterInvoiceUri().toUriString()), isAuthorized());

		profileOperations = new ProfileTemplate(createRestResource(
				uriProvider.partnerCenterProfileUri().toUriString()), isAuthorized());

		utilityOperations = new UtilityTemplate(createRestResource(
				uriProvider.partnerCenterBuilder().pathSegment("v1").toUriString()), isAuthorized());

		userOperations = new UserTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}

	private RestResource createRestResource(String baseUri) {
		return new RestResource(getRestTemplate(), baseUri);
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(factory);
		restTemplate.setRequestFactory(requestFactory);
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

	@Override
	public ProfileOperations getProfileOperations() {
		return profileOperations;
	}

	@Override
	public UtilityOperations getUtilityOperations() {
		return utilityOperations;
	}

	@Override
	public UserOperations getUserOperations() {
		return userOperations;
	}

	@Override
	public void enableSlf4j(LogLevel level) {
		getRestTemplate().getInterceptors()
				.add(new LoggingRequestInterceptor(createSlf4jApiLogger(getClass(), level)));
	}

	public String getDomain() {
		return this.uriProvider.getDomain();
	}
}
