package org.springframework.social.partnercenter.serialization;

public class JsonSerializationException extends RuntimeException{

	public JsonSerializationException(Throwable cause) {
		super("An error occurred", cause);
	}
}
