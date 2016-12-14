package org.springframework.social.partnercenter.oauth2;

public enum PartnerCenterGrantType {
	CLIENT_CREDENTIALS("client_credentials"),
	JWT_TOKEN("jwt_token"),
	PASSWORD("password");

	public static String CLIENT_CREDENTIALS_VALUE = "client_credentials";

	private String value;

	PartnerCenterGrantType(String value){
		this.value = value;
	}

	public String asString(){
		return value;
	}
}
