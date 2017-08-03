package org.springframework.social.partnercenter.http.client.retry;

import org.springframework.retry.support.RetryTemplate;

public interface RetryBuilder {
	RetryTemplate DEFAULT_EXPONENTIAL_RETRY = ExponentialRetryBuilder.builder().maxAttempts(3).initialInterval(100).multiplier(1.5).maxInterval(225).build();

	RetryTemplate build();
}
