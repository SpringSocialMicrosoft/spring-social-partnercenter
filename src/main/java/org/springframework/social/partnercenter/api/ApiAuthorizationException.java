package org.springframework.social.partnercenter.api;

import java.util.List;

import org.springframework.social.partnercenter.serialization.Json;

public class ApiAuthorizationException extends ApiException{
	AuthorizationFault authorizationFault;

	public ApiAuthorizationException(String message, Throwable cause, AuthorizationFault authorizationFault) {
		super(message, cause);
		this.authorizationFault = authorizationFault;
	}

	public ApiAuthorizationException(String message, Throwable cause, String authorizationFault) {
		super(message, cause);
		try {
			this.authorizationFault = Json.fromJson(authorizationFault, AuthorizationFault.class);
		} catch (Exception e){
			this.authorizationFault = new AuthorizationFault();
			this.authorizationFault.setErrorDescription(authorizationFault);
		}
	}

	public String getError() {
		return authorizationFault.getError();
	}

	public String getErrorDescription() {
		return authorizationFault.getErrorDescription();
	}

	public List<Integer> getErrorCodes() {
		return authorizationFault.getErrorCodes();
	}

	public String getTimestamp() {
		return authorizationFault.getTimestamp();
	}

	public String getTraceId() {
		return authorizationFault.getTraceId();
	}

	public String getCorrelation_id() {
		return authorizationFault.getCorrelationId();
	}
}
