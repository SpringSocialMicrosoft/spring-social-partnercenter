package org.springframework.social.partnercenter.http.client.retry;

import org.springframework.retry.support.RetryTemplate;

public interface RetryBuilder {
	static RetryTemplate defaultRetry(){
		return exponential().maxAttempts(3).initialInterval(100).multiplier(1.5).maxInterval(225).build();
	}
	static ExponentialRetryBuilder exponential(){
		return ExponentialRetryBuilder.builder();
	}

	RetryTemplate build();
}
