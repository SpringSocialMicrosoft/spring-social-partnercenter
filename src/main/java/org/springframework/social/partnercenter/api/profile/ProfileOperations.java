package org.springframework.social.partnercenter.api.profile;

import org.springframework.http.ResponseEntity;

public interface ProfileOperations {
	ResponseEntity<LegalBusinessProfile> getLegalBusinessProfile();
	ResponseEntity<LegalBusinessProfile> updateLegalBusinessProfile(LegalBusinessProfile legalBusinessProfile);
	ResponseEntity<OrganizationProfile> getOrganizationProfile();
	ResponseEntity<OrganizationProfile> updateOrganizationProfile(OrganizationProfile organizationProfile);
	ResponseEntity<BillingProfile> getPartnerBillingProfile();
	ResponseEntity<BillingProfile> updatePartnerBillingProfile(BillingProfile billingProfile);
	ResponseEntity<MPNProfile> getMPNProfile();
	ResponseEntity<SupportProfile> getSupportProfile();
	ResponseEntity<SupportProfile> updateSupportProfile(SupportProfile supportProfile);
	ResponseEntity<Partner> getPartner(String mpnId);
//	PartnerCenterResponse<Subscription> getAllSubscriptionsForPartner();
}
