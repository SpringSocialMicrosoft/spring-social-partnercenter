package org.springframework.social.partnercenter.api.order.offer;

import org.springframework.social.partnercenter.api.order.response.GetOfferCategoriesResponse;
import org.springframework.social.partnercenter.api.order.response.OfferListResponse;

public interface OfferOperations {
	GetOfferCategoriesResponse getOfferCategories(String countryId);
	OfferListResponse getOffersForMarket(String countryId);
	Offer getOfferById(String offerId, String countryId);
	OfferListResponse getAddOnOffersForOffer(String offerId, String countryId);
}
