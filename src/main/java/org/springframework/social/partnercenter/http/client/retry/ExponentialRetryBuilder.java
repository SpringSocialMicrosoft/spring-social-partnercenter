package org.springframework.social.partnercenter.http.client.retry;

import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class ExponentialRetryBuilder implements RetryBuilder {
	private int maxAttempts;
	private int initialInterval;
	private double multiplier;
	private int maxInterval;

	public ExponentialRetryBuilder maxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
		return this;
	}

	public ExponentialRetryBuilder initialInterval(int initialInterval) {
		this.initialInterval = initialInterval;
		return this;
	}

	public ExponentialRetryBuilder multiplier(double multiplier) {
		this.multiplier = multiplier;
		return this;
	}

	public ExponentialRetryBuilder maxInterval(int maxInterval) {
		this.maxInterval = maxInterval;
		return this;
	}

	public static ExponentialRetryBuilder builder(){
		return new ExponentialRetryBuilder();
	}
	@Override
	public RetryTemplate build() {
		RetryTemplate template = new RetryTemplate();
		template.setBackOffPolicy(buildBackoffPolicy(initialInterval, multiplier, maxInterval));
		template.setRetryPolicy(buildRetryPolicy(maxAttempts));
		return template;
	}

	private static BackOffPolicy buildBackoffPolicy(int initialInterval, double multiplier, int maxInterval) {
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(initialInterval);
		backOffPolicy.setMultiplier(multiplier);
		backOffPolicy.setMaxInterval(maxInterval);
		return backOffPolicy;
	}

	private static SimpleRetryPolicy buildRetryPolicy(int maxAttemtps) {
		SimpleRetryPolicy policy = new SimpleRetryPolicy();
		policy.setMaxAttempts(maxAttemtps);
		return policy;
	}
}
