package org.springframework.social.partnercenter.api.admin;

import static java.util.Objects.nonNull;

import java.util.Collection;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.PartnerCenterTemplate;
import org.springframework.social.partnercenter.api.admin.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.admin.customer.impl.AdminCustomerTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.http.client.RetryRestResource;
import org.springframework.social.partnercenter.http.client.retry.RetryService;

public class PartnerCenterAdminTemplate extends PartnerCenterTemplate implements PartnerCenterAdmin {
	private final AdminCustomerOperations adminCustomerOperations;

	private PartnerCenterAdminTemplate(String accessToken, String version) {
		this(null, accessToken, version);
	}

	private PartnerCenterAdminTemplate(RetryTemplate retryTemplate, String accessToken, String version){
		super(retryTemplate, accessToken, version);

		adminCustomerOperations = new AdminCustomerTemplate(createRestResource(UriProvider.partnerCenterCustomerUri().toUriString(), retryTemplate), isAuthorized());
	}

	private RestResource createRestResource(String baseUri, RetryTemplate retryTemplate){
		if(nonNull(retryTemplate)){
			return new RetryRestResource(getRestTemplate(), baseUri, new RetryService(retryTemplate));
		}
		return new RestResource(getRestTemplate(), baseUri);
	}

	public PartnerCenterAdminTemplate(String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(null, accessToken, version, interceptors);
	}

	public PartnerCenterAdminTemplate(RetryTemplate retryTemplate, String accessToken, String version, Collection<ClientHttpRequestInterceptor> interceptors) {
		this(retryTemplate, accessToken, version);
		interceptors.forEach(interceptor -> getRestTemplate().getInterceptors().add(interceptor));
	}

	@Override
	public AdminCustomerOperations getAdminCustomerOperations() {
		return adminCustomerOperations;
	}
}
