package org.springframework.social.partnercenter.api;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.social.partnercenter.serialization.Json;
import org.springframework.web.client.HttpStatusCodeException;

public class ApiFaultException extends ApiException{
	private ApiFault payload;
	private HttpStatus httpStatus;
	private HttpStatusCodeException cause;

	public ApiFaultException(String message, HttpStatusCodeException cause, HttpStatus status, ApiFault payload) {
		super(message, cause);
		this.httpStatus = status;
		this.payload = payload;
		this.cause = cause;
	}
	public ApiFaultException(String message, HttpStatusCodeException cause, String payload) {
		super(message, cause);
		try {
			this.cause = cause;
			this.payload = Json.fromJson(payload, ApiFault.class);
		} catch (Exception e){
			this.payload = new ApiFault();
			this.payload.setErrorMessage(payload);
		}
	}
	public ApiFaultException(String message, HttpStatusCodeException cause, HttpStatus status, String payload) {
		super(message, cause);
		try {
			this.cause = cause;
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

	@Deprecated
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public HttpStatus getStatusCode() {
		return cause.getStatusCode();
	}

	public String getStatusText() {
		return cause.getStatusText();
	}

	public HttpHeaders getResponseHeaders() {
		return cause.getResponseHeaders();
	}
}
