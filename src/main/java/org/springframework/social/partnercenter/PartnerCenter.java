package org.springframework.social.partnercenter;

import org.springframework.social.ApiBinding;
import org.springframework.social.partnercenter.api.billing.invoicing.InvoiceOperations;
import org.springframework.social.partnercenter.api.billing.pricing.PricingOperations;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.profile.ProfileOperations;
import org.springframework.social.partnercenter.api.utilities.UtilityOperations;
import org.springframework.social.partnercenter.http.logging.LogLevel;

public interface PartnerCenter extends ApiBinding {
	String PROVIDER_ID = "partner-center";
	SubscriptionOperations getSubscriptionOperations();
	OrderOperations getOrderOperations();
	CustomerOperations getCustomerOperations();
	OfferOperations getOfferOperations();
	UsageOperations getUsageOperations();
	PricingOperations getPricingOperations();
	InvoiceOperations getInvoiceOperations();
	ProfileOperations getProfileOperations();
	UtilityOperations getUtilityOperations();
	UserOperations getUserOperations();
	void enableSlf4j(LogLevel level);
	String getDomain();
}
