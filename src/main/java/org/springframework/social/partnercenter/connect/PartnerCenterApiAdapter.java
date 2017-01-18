package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.profile.LegalBusinessProfile;

public class PartnerCenterApiAdapter implements ApiAdapter<PartnerCenter>{
	@Override
	public boolean test(PartnerCenter api) {
		return false;
	}

	@Override
	public void setConnectionValues(PartnerCenter api, ConnectionValues values) {

	}

	@Override
	public UserProfile fetchUserProfile(PartnerCenter api) {
		LegalBusinessProfile legalBusinessProfile = api.getProfileOperations().getLegalBusinessProfile().getBody();
		return new UserProfileBuilder()
				.setEmail(legalBusinessProfile.getPrimaryContact().getEmail())
				.setFirstName(legalBusinessProfile.getPrimaryContact().getFirstName())
				.setLastName(legalBusinessProfile.getPrimaryContact().getLastName())
				.setName(legalBusinessProfile.getCompanyName())
				.setUsername(legalBusinessProfile.getPrimaryContact().getEmail())
				.build();
	}

	@Override
	public void updateStatus(PartnerCenter api, String message) {

	}
}
