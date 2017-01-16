package org.springframework.social.partnercenter.http.client.retry;

import org.springframework.retry.support.RetryTemplate;

public interface RetryBuilder {
	RetryTemplate DEFAULT_EXPONENTIAL_RETRY = ExponentialRetryBuilder.builder().maxAttempts(5).initialInterval(200).multiplier(2.0).maxInterval(1500).build();

	RetryTemplate build();
}
