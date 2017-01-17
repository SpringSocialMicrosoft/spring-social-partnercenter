package org.springframework.social.partnercenter.api;

import static java.util.Objects.nonNull;

import java.util.Collection;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.support.RetryTemplate;
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
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.api.customer.user.impl.UserTemplate;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.order.offer.impl.OfferTemplate;
import org.springframework.social.partnercenter.api.order.impl.OrderTemplate;
import org.springframework.social.partnercenter.api.order.subscription.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.api.profile.ProfileOperations;
import org.springframework.social.partnercenter.api.profile.impl.ProfileTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.api.utilities.UtilityOperations;
import org.springframework.social.partnercenter.api.utilities.impl.UtilityTemplate;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.http.client.RetryRestResource;
import org.springframework.social.partnercenter.http.client.retry.ExponentialRetryBuilder;
import org.springframework.social.partnercenter.http.client.retry.RetryService;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.http.logging.LoggingRequestInterceptor;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.http.logging.Slf4jHttpRequestResponseLogger;
import org.springframework.web.client.RestTemplate;

public class PartnerCenterTemplate extends AbstractOAuth2ApiBinding implements PartnerCenter {
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

	private PartnerCenterTemplate(String accessToken, String version) {
		this(null, accessToken, version);
	}

	private PartnerCenterTemplate(RetryTemplate retryTemplate, String accessToken, String version){
		super(accessToken);
		addVersionInterceptor(version);
		subscriptionOperations = new SubscriptionTemplate(createRestResource(
				UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate),
				isAuthorized());

		orderOperations = new OrderTemplate(createRestResource(
				UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate), isAuthorized());

		customerOperations = new CustomerTemplate(createRestResource(
				UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate) ,isAuthorized());

		offerOperations = new OfferTemplate(createRestResource(
				UriProvider.partnerCenterOfferUri().toUriString(), retryTemplate), isAuthorized());

		usageOperations = new UsageTemplate(createRestResource(
				UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate), isAuthorized());

		pricingOperations = new PricingTemplate(createRestResource(
				UriProvider.partnerCenterPricingUri().toUriString(), retryTemplate), isAuthorized());

		invoiceOperations = new InvoiceTemplate(createRestResource(
				UriProvider.partnerCenterInvoiceUri().toUriString(), retryTemplate), isAuthorized());

		profileOperations = new ProfileTemplate(createRestResource(
				UriProvider.partnerCenterProfileUri().toUriString(), retryTemplate), isAuthorized());

		utilityOperations = new UtilityTemplate(createRestResource(
				UriProvider.partnerCenterBuilder().pathSegment("v1").toUriString(), retryTemplate), isAuthorized());

		userOperations = new UserTemplate(createRestResource(
				UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate), isAuthorized());
	}

	public PartnerCenterTemplate(String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(null, accessToken, version, interceptors);
	}

	public PartnerCenterTemplate(RetryTemplate retryTemplate, String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(retryTemplate, accessToken, version);
		interceptors.forEach(interceptor -> getRestTemplate().getInterceptors().add(interceptor));
	}

	private RestResource createRestResource(String baseUri, RetryTemplate retryTemplate){
		if(nonNull(retryTemplate)){
			return new RetryRestResource(getRestTemplate(), baseUri, new RetryService(retryTemplate));
		}
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
				.add(new LoggingRequestInterceptor(new Slf4jHttpRequestResponseLogger(getClass(), level, HttpStatus.OK, HttpStatus.NOT_FOUND)));
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
