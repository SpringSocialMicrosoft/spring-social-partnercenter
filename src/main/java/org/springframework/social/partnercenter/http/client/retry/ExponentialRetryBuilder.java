package org.springframework.social.partnercenter.http.client.retry;

import static java.util.Objects.nonNull;

import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class ExponentialRetryBuilder implements RetryBuilder {
	private final static int DEFAULT_MAX_ATTEMPTS = 5;
	private final static int DEFAULT_INITIAL_INTERVAL= 200;
	private final static double DEFAULT_MULTIPLIER = 2.0;
	private final static int DEFAULT_MAX_INTERVAL = 1500;

	private Integer maxAttempts;
	private Integer initialInterval;
	private Double multiplier;
	private Integer maxInterval;

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
		template.setBackOffPolicy(buildBackoffPolicy(
				nonNull(initialInterval) ? initialInterval : DEFAULT_INITIAL_INTERVAL,
				nonNull(multiplier) ? multiplier : DEFAULT_MULTIPLIER,
				nonNull(maxInterval) ? maxInterval : DEFAULT_MAX_INTERVAL));
		template.setRetryPolicy(buildRetryPolicy(nonNull(maxAttempts) ? maxAttempts : DEFAULT_MAX_ATTEMPTS));
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
