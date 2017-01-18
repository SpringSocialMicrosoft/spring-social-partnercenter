package org.springframework.social.partnercenter.api.order.offer.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.http.client.RestResource;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.order.offer.Offer;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.response.GetOfferCategoriesListResponse;
import org.springframework.social.partnercenter.api.order.response.OfferListResponse;

public class OfferTemplate extends AbstractTemplate implements OfferOperations{
	private static final String COUNTRY = "country";
	private RestResource restResource;

	public OfferTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<GetOfferCategoriesListResponse> getOfferCategories(String countryId) {
		return restResource.request().pathSegment("v1", "offercategories")
				.queryParam(COUNTRY, countryId)
				.get(GetOfferCategoriesListResponse.class);
	}

	@Override
	public ResponseEntity<OfferListResponse> getOffersForMarket(String countryId) {
		return restResource.request()
				.queryParam(COUNTRY, countryId)
				.get(OfferListResponse.class);
	}

	@Override
	public ResponseEntity<Offer> getOfferById(String offerId, String countryId) {
		return restResource.request()
				.pathSegment(offerId)
				.queryParam(COUNTRY, countryId)
				.get(Offer.class);
	}

	@Override
	public ResponseEntity<OfferListResponse> getAddOnOffersForOffer(String offerId, String countryId) {
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
