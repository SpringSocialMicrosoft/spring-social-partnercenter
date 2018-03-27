package org.springframework.social.partnercenter;

import org.springframework.social.partnercenter.api.analytics.AnalyticsOperations;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.customer.user.role.DirectoryRoleOperations;
import org.springframework.social.partnercenter.api.customer.user.role.RoleOperations;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;
import org.springframework.social.partnercenter.api.support.SupportOperations;

public interface PartnerCenterAdmin extends PartnerCenter {
	String PROVIDER_ID = "partner-center-admin";

	AdminCustomerOperations getCustomerOperations();
	AdminUserOperations getUserOperations();
	AdminOrderOperations getOrderOperations();
	AdminSubscriptionOperations getSubscriptionOperations();
	AnalyticsOperations getAnalyticsOperations();
	SupportOperations getSupportOperations();
	DirectoryRoleOperations getDirectoryRoleOperations();
	RoleOperations getRoleOperations();
}
