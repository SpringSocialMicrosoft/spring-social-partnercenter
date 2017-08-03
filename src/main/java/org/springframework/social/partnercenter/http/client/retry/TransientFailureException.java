package org.springframework.social.partnercenter.http.client.retry;

public class TransientFailureException extends RuntimeException{
	public TransientFailureException(Throwable cause) {
		super(cause);
	}
}
