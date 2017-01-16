package org.springframework.social.partnercenter.http.client;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.partnercenter.http.client.retry.RetryService;
import org.springframework.web.client.RestTemplate;

public class RetryRestResource extends RestResource {
	private final RetryService retryService;

	public RetryRestResource(RestTemplate restTemplate, String resourceBaseUri, RetryService retryService) {
		super(restTemplate, resourceBaseUri);
		this.retryService = retryService;
	}

	@Override
	public <T, R> R execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType) {
		return retryService.doWithRetry(() ->
				getRestTemplate().exchange(
						uri,
						httpMethod,
						entity,
						responseType
				).getBody()
		);
	}

	@Override
	public <T, R> R execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType) {
		return retryService.doWithRetry(() ->
				getRestTemplate().exchange(
						uri,
						httpMethod,
						entity,
						responseType
				).getBody()
		);
	}

	@Override
	public void delete(URI uri) {
			retryService.doWithRetry(() -> getRestTemplate().delete(uri));
	}
}
