package org.springframework.social.partnercenter.api.profile;

public interface ProfileOperations {
	LegalBusinessProfile getLegalBusinessProfile();
	LegalBusinessProfile updateLegalBusinessProfile(LegalBusinessProfile legalBusinessProfile);
	OrganizationProfile getOrganizationProfile();
	OrganizationProfile updateOrganizationProfile(OrganizationProfile organizationProfile);
	BillingProfile getPartnerBillingProfile();
	BillingProfile updatePartnerBillingProfile(BillingProfile billingProfile);
	MPNProfile getMPNProfile();
	SupportProfile getSupportProfile();
	SupportProfile updateSupportProfile(SupportProfile supportProfile);
	Partner getPartner(String mpnId);
//	PartnerCenterResponse<Subscription> getAllSubscriptionsForPartner();
}
