package org.springframework.social.partnercenter.api;

import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.AdminCustomerTemplate;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.impl.AdminUserTemplate;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.impl.AdminOrderTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PartnerCenterAdminTemplate extends PartnerCenterTemplate implements PartnerCenterAdmin {
	private final AdminCustomerOperations adminCustomerOperations;
	private final AdminUserOperations adminUserOperations;
	private final AdminOrderOperations adminOrderOperations;

	public PartnerCenterAdminTemplate(UriProvider uriProvider, String accessToken, String version){
		super(uriProvider, accessToken, version);

		adminCustomerOperations = new AdminCustomerTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());
		adminUserOperations = new AdminUserTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());
		adminOrderOperations = new AdminOrderTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().toUriString()), isAuthorized());
	}

	private RestResource createRestResource(String baseUri){
		return new RestResource(getRestTemplate(), baseUri);
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
}
