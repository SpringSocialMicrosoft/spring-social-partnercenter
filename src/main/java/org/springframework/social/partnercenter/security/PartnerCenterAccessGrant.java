package org.springframework.social.partnercenter.security;

import org.springframework.social.oauth2.AccessGrant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerCenterAccessGrant extends AccessGrant {
	@JsonProperty("id_token")
	private String idToken;

	public PartnerCenterAccessGrant(String accessToken, String scope, String refreshToken, String idToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
		this.idToken = idToken;
	}

	public String getIdToken() {
		return idToken;
	}

	public PartnerCenterAccessGrant setIdToken(String idToken) {
		this.idToken = idToken;
		return this;
	}
}
