package org.springframework.social.partnercenter.api;

import java.net.URI;

import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.AdminCustomerTemplate;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.impl.AdminUserTemplate;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.impl.AdminOrderTemplate;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.impl.AdminSubscriptionTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PartnerCenterAdminTemplate extends PartnerCenterTemplate implements PartnerCenterAdmin {
	private final AdminCustomerOperations adminCustomerOperations;
	private final AdminUserOperations adminUserOperations;
	private final AdminOrderOperations adminOrderOperations;
	private final AdminSubscriptionOperations adminSubscriptionOperations;

	public PartnerCenterAdminTemplate(UriProvider uriProvider, String accessToken, String version){
		super(uriProvider, accessToken, version);

		adminCustomerOperations = new AdminCustomerTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminUserOperations = new AdminUserTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminOrderOperations = new AdminOrderTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminSubscriptionOperations = new AdminSubscriptionTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
	}

	private RestResource createRestResource(URI baseUri){
		return new RestClient(getRestTemplate(), baseUri);
	}

	@Override
	public AdminCustomerOperations getCustomerOperations() {
		return adminCustomerOperations;
	}

	@Override
	public AdminUserOperations getUserOperations() {
		return adminUserOperations;
	}

	@Override
	public AdminOrderOperations getOrderOperations() {
		return adminOrderOperations;
	}

	@Override
	public AdminSubscriptionOperations getSubscriptionOperations() {
		return adminSubscriptionOperations;
	}
}
