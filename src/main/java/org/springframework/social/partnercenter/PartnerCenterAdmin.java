package org.springframework.social.partnercenter;

import org.springframework.social.partnercenter.api.analytics.AnalyticsOperations;
import org.springframework.social.partnercenter.api.customer.AdminCustomerOperations;
import org.springframework.social.partnercenter.api.customer.user.AdminUserOperations;
import org.springframework.social.partnercenter.api.order.AdminOrderOperations;
import org.springframework.social.partnercenter.api.order.subscription.AdminSubscriptionOperations;

public interface PartnerCenterAdmin extends PartnerCenter {
	String PROVIDER_ID = "partner-center-admin";

	AdminCustomerOperations getCustomerOperations();
	AdminUserOperations getUserOperations();
	AdminOrderOperations getOrderOperations();
	AdminSubscriptionOperations getSubscriptionOperations();
	AnalyticsOperations getAnalyticsOperations();
}
