package org.springframework.social.partnercenter.api.order.impl;

import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.order.Offer;
import org.springframework.social.partnercenter.api.order.OfferOperations;
import org.springframework.social.partnercenter.api.order.response.GetOfferCategoriesResponse;
import org.springframework.social.partnercenter.api.order.response.OfferListResponse;
import org.springframework.social.partnercenter.api.uri.UriProvider;

public class OfferTemplate extends AbstractTemplate implements OfferOperations{
	private static final String COUNTRY = "country";
	private RestResource restResource;

	public OfferTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public GetOfferCategoriesResponse getOfferCategories(String countryId) {
		return restResource.get(UriProvider.partnerCenterBuilder()
				.pathSegment("v1", "offercategories")
				.queryParam(COUNTRY, countryId)
				.build().toUri(),
				GetOfferCategoriesResponse.class);
	}

	@Override
	public OfferListResponse getOffersForMarket(String countryId) {
		return restResource.request()
				.queryParam(COUNTRY, countryId)
				.get(OfferListResponse.class);
	}

	@Override
	public Offer getOfferById(String offerId, String countryId) {
		return restResource.request()
				.pathSegment(offerId)
				.queryParam(COUNTRY, countryId)
				.get(Offer.class);
	}

	@Override
	public OfferListResponse getAddOnOffersForOffer(String offerId, String countryId) {
		return restResource.request()
				.pathSegment(offerId, "addons")
				.queryParam(COUNTRY, countryId)
				.get(OfferListResponse.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
