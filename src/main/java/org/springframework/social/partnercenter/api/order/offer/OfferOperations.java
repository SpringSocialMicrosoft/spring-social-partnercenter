package org.springframework.social.partnercenter.api.order.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface OfferOperations {
	ResponseEntity<PartnerCenterResponse<OfferCategory>>  getOfferCategories(String countryId);
	ResponseEntity<PartnerCenterResponse<Offer>> getOffersForMarket(String countryId);
	ResponseEntity<Offer> getOfferById(String offerId, String countryId);
	ResponseEntity<PartnerCenterResponse<Offer>> getAddOnOffersForOffer(String offerId, String countryId);
}
