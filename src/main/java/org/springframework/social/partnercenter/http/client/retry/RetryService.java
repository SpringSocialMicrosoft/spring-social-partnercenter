package org.springframework.social.partnercenter.http.client.retry;

import java.util.function.Supplier;

import org.springframework.retry.support.RetryTemplate;

public class RetryService {
	RetryTemplate retryTemplate;

	public RetryService(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}

	public <R> R doWithRetry(Supplier<R> function) throws RuntimeException {
		return this.retryTemplate.execute(context -> function.get());
	}
	public Boolean doWithRetry(Runnable function) throws RuntimeException {
		return this.retryTemplate.execute(context -> {
			function.run();
			return true;
		});
	}
}
