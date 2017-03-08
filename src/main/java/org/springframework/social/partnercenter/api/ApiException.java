package org.springframework.social.partnercenter.api;

public class ApiException extends RuntimeException{

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
