package org.springframework.social.partnercenter.impl;

import static java.util.Optional.ofNullable;

import java.net.URI;
import java.util.Optional;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestService;
import org.springframework.social.partnercenter.operations.SubscriptionOperations;
import org.springframework.social.partnercenter.model.order.Subscription;
import org.springframework.social.partnercenter.uri.SubscriptionUriProvider;

public class SubscriptionTemplate extends AbstractTemplate implements SubscriptionOperations {

	private final RestService restService;

	public SubscriptionTemplate(RestService restService, boolean isAuthorized) {
		super(isAuthorized);
		this.restService = restService;
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}

	@Override
	public Optional<Subscription> getById(String customerTenantId, String id) {
		URI uri = SubscriptionUriProvider.buildSubscriptionUri(customerTenantId, id);

		return ofNullable(restService.get(uri, Subscription.class));
	}
}
