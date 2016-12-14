package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.partnercenter.PartnerCenter;

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
		return null;
	}

	@Override
	public void updateStatus(PartnerCenter api, String message) {

	}
}
