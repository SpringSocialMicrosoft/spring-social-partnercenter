package org.springframework.social.partnercenter.api.order.offer.impl;

import static org.springframework.util.Assert.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.order.offer.Offer;
import org.springframework.social.partnercenter.api.order.offer.OfferCategory;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class OfferTemplate extends AbstractTemplate implements OfferOperations{
	private static final String COUNTRY = "country";
	private RestResource restResource;

	public OfferTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<OfferCategory>> getOfferCategories(String countryId) {
		notNull(countryId, "[Assertion failed] - countryId argument must be null");
		return restResource.request().pathSegment("v1", "offercategories")
				.queryParam(COUNTRY, countryId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<OfferCategory>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Offer>> getOffersForMarket(String countryId) {
		notNull(countryId, "[Assertion failed] - countryId argument must be null");
		return restResource.request()
				.queryParam(COUNTRY, countryId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Offer>>() {});
	}

	@Override
	public ResponseEntity<Offer> getOfferById(String offerId, String countryId) {
		notNull(countryId, "[Assertion failed] - countryId argument must be null");
		notNull(offerId, "[Assertion failed] - offerId argument must be null");
		return restResource.request()
				.pathSegment(offerId)
				.queryParam(COUNTRY, countryId)
				.get(Offer.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Offer>> getAddOnOffersForOffer(String offerId, String countryId) {
		notNull(countryId, "[Assertion failed] - countryId argument must be null");
		notNull(offerId, "[Assertion failed] - offerId argument must be null");
		return restResource.request()
				.pathSegment(offerId, "addons")
				.queryParam(COUNTRY, countryId)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Offer>>() {});
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
