package org.springframework.social.partnercenter;

import org.springframework.social.ApiBinding;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.order.OfferOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.SubscriptionOperations;

public interface PartnerCenter extends ApiBinding {
	String PROVIDER_ID = "partner-center";
	SubscriptionOperations getSubscriptionOperations();
	OrderOperations getOrderOperations();
	CustomerOperations getCustomerOperations();
	OfferOperations getOfferOperations();
}
