package org.springframework.social.partnercenter;

import org.springframework.social.ApiBinding;
import org.springframework.social.partnercenter.operations.OrderOperations;
import org.springframework.social.partnercenter.operations.SubscriptionOperations;

public interface PartnerCenter extends ApiBinding {
	String PROVIDER_ID = "partner-center";
	SubscriptionOperations getSubscriptionOperations();
	OrderOperations getOrderOperations();
}
