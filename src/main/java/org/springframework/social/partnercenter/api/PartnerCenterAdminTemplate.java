package org.springframework.social.partnercenter.api;

import java.net.URI;

import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.api.analytics.AnalyticsOperations;
import org.springframework.social.partnercenter.api.analytics.impl.AnalyticsTemplate;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.impl.AdminCustomerTemplate;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.impl.AdminUserTemplate;
import org.springframework.social.partnercenter.api.customer.user.role.DirectoryRoleOperations;
import org.springframework.social.partnercenter.api.customer.user.role.RoleOperations;
import org.springframework.social.partnercenter.api.customer.user.role.impl.DirectoryRoleTemplate;
import org.springframework.social.partnercenter.api.customer.user.role.impl.RoleTemplate;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.impl.AdminOrderTemplate;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.order.subscription.impl.AdminSubscriptionTemplate;
import org.springframework.social.partnercenter.api.partner.AdminPartnerOperations;
import org.springframework.social.partnercenter.api.partner.impl.AdminPartnerTemplate;
import org.springframework.social.partnercenter.api.relationships.AdminRelationshipOperations;
import org.springframework.social.partnercenter.api.relationships.impl.AdminRelationshipTemplate;
import org.springframework.social.partnercenter.api.support.SupportOperations;
import org.springframework.social.partnercenter.api.support.impl.SupportTemplate;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.http.client.RestResource;

public class PartnerCenterAdminTemplate extends PartnerCenterTemplate implements PartnerCenterAdmin {
	private final AdminCustomerOperations adminCustomerOperations;
	private final AdminUserOperations adminUserOperations;
	private final AdminOrderOperations adminOrderOperations;
	private final AdminSubscriptionOperations adminSubscriptionOperations;
	private final AnalyticsOperations analyticsOperations;
	private final SupportOperations supportOperations;
	private final DirectoryRoleOperations directoryRoleOperations;
	private final RoleOperations roleOperations;
	private final AdminRelationshipOperations adminRelationshipOperations;
	private final AdminPartnerOperations adminPartnerOperations;

	public PartnerCenterAdminTemplate(UriProvider uriProvider, String accessToken, String version){
		super(uriProvider, accessToken, version);
		adminCustomerOperations = new AdminCustomerTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminUserOperations = new AdminUserTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminOrderOperations = new AdminOrderTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		adminSubscriptionOperations = new AdminSubscriptionTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		analyticsOperations = new AnalyticsTemplate(createRestResource(uriProvider.partnerBaseUri().build().toUri()), isAuthorized());
		supportOperations = new SupportTemplate(createRestResource(uriProvider.partnerBaseUri().build().toUri()), isAuthorized());
		directoryRoleOperations = new DirectoryRoleTemplate(createRestResource(uriProvider.partnerCenterCustomerUri().build().toUri()), isAuthorized());
		roleOperations = new RoleTemplate(createRestResource(uriProvider.partnerBaseUri().build().toUri()), isAuthorized());
		adminRelationshipOperations = new AdminRelationshipTemplate(createRestResource(uriProvider.partnerCenterRelationshipsUri().build().toUri()), isAuthorized());
		adminPartnerOperations = new AdminPartnerTemplate(createRestResource(uriProvider.partnerBaseUri().build().toUri()), isAuthorized());
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

	@Override
	public AnalyticsOperations getAnalyticsOperations() {
		return analyticsOperations;
	}

	@Override
	public SupportOperations getSupportOperations() {
		return supportOperations;
	}

	@Override
	public DirectoryRoleOperations getDirectoryRoleOperations() {
		return directoryRoleOperations;
	}

	@Override
	public RoleOperations getRoleOperations() {
		return roleOperations;
	}

	@Override
	public AdminRelationshipOperations getRelationshipOperations() {
		return adminRelationshipOperations;
	}

	@Override
	public AdminPartnerOperations getPartnerOperations() {
		return adminPartnerOperations;
	}
}
