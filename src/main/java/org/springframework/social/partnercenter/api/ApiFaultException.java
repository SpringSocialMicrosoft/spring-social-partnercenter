package org.springframework.social.partnercenter.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.social.partnercenter.serialization.Json;

public class ApiFaultException extends ApiException{
	private ApiFault payload;
	private HttpStatus httpStatus;

	public ApiFaultException(String message, Throwable cause, HttpStatus status, ApiFault payload) {
		super(message, cause);
		this.httpStatus = status;
		this.payload = payload;
	}
	public ApiFaultException(String message, Throwable cause, String payload) {
		super(message, cause);
		try {
			this.payload = Json.fromJson(payload, ApiFault.class);
		} catch (Exception e){
			this.payload = new ApiFault();
			this.payload.setErrorMessage(payload);
		}
	}
	public ApiFaultException(String message, Throwable cause, HttpStatus status, String payload) {
		super(message, cause);
		try {
			this.httpStatus = status;
			this.payload = Json.fromJson(payload, ApiFault.class);
		} catch (Exception e){
			this.payload = new ApiFault();
			this.payload.setErrorMessage(payload);
		}
	}

	public String getErrorCode() {
		return payload.getErrorCode();
	}

	public String getErrorMessage() {
		return payload.getErrorMessage();
	}


	public Map<String, String> getAttributes() {
		return payload.getAttributes();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
