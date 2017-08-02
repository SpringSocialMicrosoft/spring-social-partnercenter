package org.springframework.social.partnercenter.api.customer.user.impl;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.customer.user.CustomerUser;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UserTemplate extends PagingResourceTemplate<CustomerUser> implements UserOperations{
	private RestResource restResource;

	public UserTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized);
		this.restResource = restResource;
	}



	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
