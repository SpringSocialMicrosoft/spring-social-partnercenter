package org.springframework.social.partnercenter.api;

import static java.util.Objects.nonNull;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.AdminCustomerTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.http.client.RetryRestResource;
import org.springframework.social.partnercenter.http.client.retry.RetryService;

public class PartnerCenterAdminTemplate extends PartnerCenterTemplate implements PartnerCenterAdmin {
	private final AdminCustomerOperations adminCustomerOperations;

	private PartnerCenterAdminTemplate(RetryTemplate retryTemplate, UriProvider uriProvider, String accessToken, String version){
		super(retryTemplate, uriProvider, accessToken, version);

		adminCustomerOperations = new AdminCustomerTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate), isAuthorized());
	}

	public PartnerCenterAdminTemplate(RetryTemplate retryTemplate, UriProvider uriProvider, String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(retryTemplate, uriProvider, accessToken, version);
		interceptors.forEach(interceptor -> getRestTemplate().getInterceptors().add(interceptor));
	}

	private RestResource createRestResource(String baseUri, RetryTemplate retryTemplate){
		if(nonNull(retryTemplate)){
			return new RetryRestResource(getRestTemplate(), baseUri, new RetryService(retryTemplate));
		}
		return new RestResource(getRestTemplate(), baseUri);
	}

	@Override
	public AdminCustomerOperations getCustomerOperations() {
		return adminCustomerOperations;
	}
}
