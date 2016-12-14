package org.springframework.social.partnercenter.uri;

import java.net.URI;

public class SubscriptionUriProvider {

	public static URI buildSubscriptionUri(String customerTenantId, String subscriptionId){
		return UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerTenantId, "subscriptions", subscriptionId)
				.build()
				.toUri();
	}
}
