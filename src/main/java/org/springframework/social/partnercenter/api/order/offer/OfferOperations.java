package org.springframework.social.partnercenter.api.order.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.order.response.GetOfferCategoriesListResponse;
import org.springframework.social.partnercenter.api.order.response.OfferListResponse;

public interface OfferOperations {
	ResponseEntity<GetOfferCategoriesListResponse> getOfferCategories(String countryId);
	ResponseEntity<OfferListResponse> getOffersForMarket(String countryId);
	ResponseEntity<Offer> getOfferById(String offerId, String countryId);
	ResponseEntity<OfferListResponse> getAddOnOffersForOffer(String offerId, String countryId);
}
