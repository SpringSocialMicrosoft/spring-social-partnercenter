package org.springframework.social.partnercenter.http.client.retry;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.api.ApiFaultException;

public class HttpRetryService {
	private RetryTemplate retryTemplate;
	private List<HttpStatus> statusCodeToNotRetry;

	public HttpRetryService(RetryTemplate retryTemplate, HttpStatus ... statusCodeToNotRetry) {
		this.retryTemplate = retryTemplate;
		this.statusCodeToNotRetry = asList(statusCodeToNotRetry);
	}

	public <R> R doWithRetry(Supplier<R> function) throws RuntimeException {
		return this.retryTemplate.execute(context -> {
			try {
				return function.get();
			} catch (ApiFaultException e){
				if (statusCodeToNotRetry.contains(e.getHttpStatus())){
					context.setExhaustedOnly();
				}
				throw e;
			}
		});
	}
	public Boolean doWithRetry(Runnable function) throws RuntimeException {
		return this.retryTemplate.execute(context -> {
			try {
				function.run();
			} catch (ApiFaultException e){
				if (statusCodeToNotRetry.contains(e.getHttpStatus())){
					context.setExhaustedOnly();
				}
				throw e;
			}
			return true;
		});
	}
}
