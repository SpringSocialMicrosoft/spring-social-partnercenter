package org.springframework.social.partnercenter.api;

import java.net.URI;
import java.util.Locale;
import java.util.stream.IntStream;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.audit.AuditOperations;
import org.springframework.social.partnercenter.api.audit.impl.AuditTemplate;
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
import org.springframework.social.partnercenter.connect.AddHeaderRequestInterceptor;
import org.springframework.social.partnercenter.connect.ApiVersionParameterRequestInterceptor;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.http.logging.HttpRequestResponseLoggerFactory;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.http.logging.LoggingRequestInterceptor;
import org.springframework.social.partnercenter.serialization.Json;
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
	private final AuditOperations auditOperations;

	public PartnerCenterTemplate(UriProvider uriProvider, String accessToken, String version){
		super(accessToken);
		addVersionInterceptor(version);
		this.uriProvider = uriProvider;
		subscriptionOperations = new SubscriptionTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().build().toUri()),
				isAuthorized());

		orderOperations = new OrderTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());

		customerOperations = new CustomerTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().build().toUri()) ,isAuthorized());

		offerOperations = new OfferTemplate(createRestResource(
				uriProvider.partnerCenterOfferUri().build().toUri()), isAuthorized());

		usageOperations = new UsageTemplate(uriProvider, createRestResource(
				uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());

		pricingOperations = new PricingTemplate(createRestResource(
				uriProvider.partnerCenterPricingUri().build().toUri()), isAuthorized());

		invoiceOperations = new InvoiceTemplate(createRestResource(
				uriProvider.partnerCenterInvoiceUri().build().toUri()), isAuthorized());

		profileOperations = new ProfileTemplate(createRestResource(
				uriProvider.partnerCenterProfileUri().build().toUri()), isAuthorized());

		utilityOperations = new UtilityTemplate(createRestResource(
				uriProvider.partnerCenterBuilder().pathSegment("v1").build().toUri()), isAuthorized());

		userOperations = new UserTemplate(createRestResource(
				uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());

		auditOperations = new AuditTemplate(createRestResource(uriProvider.auditUri().build().toUri()));
	}

	private RestResource createRestResource(URI baseUri){
		return new RestClient(getRestTemplate(), baseUri);
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(factory);
		IntStream.range(0, restTemplate.getMessageConverters().size())
				.forEach(idx -> {
					if (MappingJackson2HttpMessageConverter.class.isInstance(restTemplate.getMessageConverters().get(idx))) {
						restTemplate.getMessageConverters().set(idx, new MappingJackson2HttpMessageConverter(Json.instance().getObjectMapper()));
					}
				});
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
	public AuditOperations getAuditOperations() {
		return auditOperations;
	}

	@Override
	public void enableSlf4j(LogLevel level) {
		getRestTemplate().getInterceptors().removeIf(LoggingRequestInterceptor.class::isInstance);
		getRestTemplate().getInterceptors().add(new LoggingRequestInterceptor(HttpRequestResponseLoggerFactory.createSlf4jApiLogger(getClass(), level)));
	}

	@Override
	public void setLocale(Locale locale) {
		getRestTemplate().getInterceptors().removeIf(AddHeaderRequestInterceptor.class::isInstance);
		getRestTemplate().getInterceptors().add(new AddHeaderRequestInterceptor("X-Locale", locale.toLanguageTag()));
	}

	@Override
	public boolean isSlf4jEnabled() {
		return getRestTemplate().getInterceptors().stream().anyMatch(LoggingRequestInterceptor.class::isInstance);
	}

	public String getDomain(){
		return this.uriProvider.getDomain();
	}

	private void addVersionInterceptor(String apiVersion) {
		getRestTemplate().getInterceptors().add(new ApiVersionParameterRequestInterceptor(apiVersion));
	}
}
