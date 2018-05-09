package org.springframework.social.partnercenter.api.profile.impl;

import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.profile.BillingProfile;
import org.springframework.social.partnercenter.api.profile.LegalBusinessProfile;
import org.springframework.social.partnercenter.api.profile.MPNProfile;
import org.springframework.social.partnercenter.api.profile.OrganizationProfile;
import org.springframework.social.partnercenter.api.profile.Partner;
import org.springframework.social.partnercenter.api.profile.ProfileOperations;
import org.springframework.social.partnercenter.api.profile.SupportProfile;
import org.springframework.social.partnercenter.http.client.RestResource;

public class ProfileTemplate extends AbstractTemplate implements ProfileOperations{
	private final RestResource restResource;

	public ProfileTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<LegalBusinessProfile> getLegalBusinessProfile() {
		return restResource.request()
				.pathSegment("legalbusiness")
				.get(LegalBusinessProfile.class);
	}

	@Override
	public ResponseEntity<LegalBusinessProfile> updateLegalBusinessProfile(LegalBusinessProfile legalBusinessProfile) {
		notNull(legalBusinessProfile, "legalBusinessProfile");
		return restResource.request()
				.pathSegment("legalbusiness")
				.put(legalBusinessProfile, LegalBusinessProfile.class);
	}

	@Override
	public ResponseEntity<OrganizationProfile> getOrganizationProfile() {
		return restResource.request()
				.pathSegment("organization")
				.get(OrganizationProfile.class);
	}

	@Override
	public ResponseEntity<OrganizationProfile> updateOrganizationProfile(OrganizationProfile organizationProfile) {
		notNull(organizationProfile, "organizationProfile");
		return restResource.request()
				.pathSegment("organization")
				.put(organizationProfile, OrganizationProfile.class);
	}

	@Override
	public ResponseEntity<BillingProfile> getPartnerBillingProfile() {
		return restResource.request()
				.pathSegment("billing")
				.get(BillingProfile.class);
	}

	@Override
	public ResponseEntity<BillingProfile> updatePartnerBillingProfile(BillingProfile billingProfile) {
		notNull(billingProfile, "billingProfile");
		return restResource.request()
				.pathSegment("billing")
				.put(billingProfile, BillingProfile.class);
	}

	@Override
	public ResponseEntity<MPNProfile> getMPNProfile() {
		return restResource.request()
				.pathSegment("mpn")
				.get(MPNProfile.class);
	}

	@Override
	public ResponseEntity<SupportProfile> getSupportProfile() {
		return restResource.request()
				.pathSegment("support")
				.get(SupportProfile.class);
	}

	@Override
	public ResponseEntity<SupportProfile> updateSupportProfile(SupportProfile supportProfile) {
		notNull(supportProfile, "supportProfile");
		return restResource.request()
				.pathSegment("support")
				.put(supportProfile, SupportProfile.class);
	}

	@Override
	public ResponseEntity<Partner> getPartner(String mpnId) {
		notNull(mpnId, "mpnId");
		return restResource.request()
				.pathSegment("mpn")
				.queryParam("mpnId", mpnId)
				.get(Partner.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
