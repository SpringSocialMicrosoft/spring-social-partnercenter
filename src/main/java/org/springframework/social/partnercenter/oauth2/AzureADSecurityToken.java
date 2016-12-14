package org.springframework.social.partnercenter.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AzureADSecurityToken {
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("ext_expires_in")
	private String extExpiresIn;
	@JsonProperty("expires_on")
	private String expiresOn;
	@JsonProperty("not_before")
	private String notBefore;
	@JsonProperty("resource")
	private String resource;
	@JsonProperty("access_token")
	private String accessToken;

	public String getTokenType() {
		return tokenType;
	}

	public AzureADSecurityToken setTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public AzureADSecurityToken setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public String getExtExpiresIn() {
		return extExpiresIn;
	}

	public AzureADSecurityToken setExtExpiresIn(String extExpiresIn) {
		this.extExpiresIn = extExpiresIn;
		return this;
	}

	public String getExpiresOn() {
		return expiresOn;
	}

	public AzureADSecurityToken setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
		return this;
	}

	public String getNotBefore() {
		return notBefore;
	}

	public AzureADSecurityToken setNotBefore(String notBefore) {
		this.notBefore = notBefore;
		return this;
	}

	public String getResource() {
		return resource;
	}

	public AzureADSecurityToken setResource(String resource) {
		this.resource = resource;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public AzureADSecurityToken setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
}
