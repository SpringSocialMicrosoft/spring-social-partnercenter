package org.springframework.social.partnercenter.uri;

import java.net.URI;

public class CustomerUriProvider{

	public static URI buildBetByIdUri(String tenantId){
		return UriProvider.partnerCenterBuilder()
				.pathSegment("v1", "customers", tenantId)
				.build().toUri();
	}

	public static URI buildGetByCompanyNameOrDomainUri(int size, String filter){
		return UriProvider.partnerCenterCustomerApiBuilder()
				.queryParam("size", size)
				.queryParam("filter", filter)
				.build().toUri();
	}

	public static URI buildGetListUri(int size){
		return UriProvider.partnerCenterCustomerApiBuilder().queryParam("size", size).build().toUri();
	}

	public static URI buildBillingProfileUri(String customerId){
		return UriProvider.partnerCenterCustomerApiBuilder()
				.pathSegment(customerId, "profiles", "billing")
				.build().toUri();
	}
}
