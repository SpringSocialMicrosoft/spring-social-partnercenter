package org.springframework.social.partnercenter.http.client;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.http.client.retry.RetryService;
import org.springframework.web.client.RestTemplate;

public class RetryRestResource extends RestResource {
	private final RetryService retryService;

	public RetryRestResource(RestTemplate restTemplate, String resourceBaseUri, RetryService retryService) {
		super(restTemplate, resourceBaseUri);
		this.retryService = retryService;
	}

	@Override
	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType) {
		return retryService.doWithRetry(() ->
				getRestTemplate().exchange(
						uri,
						httpMethod,
						entity,
						responseType
				)
		);
	}

	@Override
	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType) {
		return retryService.doWithRetry(() ->
				getRestTemplate().exchange(
						uri,
						httpMethod,
						entity,
						responseType
				)
		);
	}

	@Override
	public ResponseEntity delete(URI uri, HttpEntity entity) {
			return retryService.doWithRetry(() -> getRestTemplate().exchange(uri, HttpMethod.DELETE, entity, String.class));
	}
}
