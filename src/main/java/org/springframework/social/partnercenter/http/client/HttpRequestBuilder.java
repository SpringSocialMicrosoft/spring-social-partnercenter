package org.springframework.social.partnercenter.http.client;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.social.partnercenter.http.PartnerCenterHttpHeaders;
import org.springframework.social.partnercenter.http.client.retry.HttpRetryService;
import org.springframework.social.partnercenter.http.client.retry.RetryBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpRequestBuilder {
	private PartnerCenterHttpHeaders headers;
	private RestResource restResource;
	private UriComponentsBuilder uriBuilder;
	private HttpRetryService httpRetryService = new HttpRetryService(RetryBuilder.DEFAULT_EXPONENTIAL_RETRY, FORBIDDEN, UNAUTHORIZED);

	HttpRequestBuilder(RestResource restResource, String resourceBaseUri, String msRequestId, String msCorrelationId){
		this.headers = new PartnerCenterHttpHeaders();
		addMicrosoftTrackingHeaders(msRequestId, msCorrelationId);
		this.restResource = restResource;
		this.uriBuilder = UriComponentsBuilder.fromUriString(resourceBaseUri);
	}

	HttpRequestBuilder(RestResource restResource, String resourceBaseUri){
		this.headers = new PartnerCenterHttpHeaders();
		addMicrosoftTrackingHeaders();
		this.restResource = restResource;
		this.uriBuilder = UriComponentsBuilder.fromUriString(resourceBaseUri);
	}

	public HttpRequestBuilder header(String key, List<String> value){
		headers.put(key, value);
		return this;
	}

	public HttpRequestBuilder header(String key, String value){
		headers.put(key, singletonList(value));
		return this;
	}

	public HttpRequestBuilder pathSegment(String ... pathSegments){
		uriBuilder.pathSegment(pathSegments);
		return this;
	}

	public HttpRequestBuilder queryParam(String name, Object... values){
		uriBuilder.queryParam(name, values);
		return this;
	}

	public HttpRequestBuilder noRetry(){
		httpRetryService = null;
		return this;
	}

	public HttpRequestBuilder withRetry(RetryTemplate retryTemplate, HttpStatus ... statusCodeToNotRetry){
		httpRetryService = new HttpRetryService(retryTemplate, statusCodeToNotRetry);
		return this;
	}

	public HttpRequestBuilder path(String path){
		uriBuilder.path(path);
		return this;
	}

	public <T, R> ResponseEntity<R> put(T payload, Class<R> aClass) {
		HttpEntity<T> entity = new HttpEntity<>(payload, headers);
		return execute(uriBuilder.build().toUri(), HttpMethod.PUT, entity, aClass);
	}

	public <T, R> ResponseEntity<R> post(T payload, Class<R> aClass) {
		HttpEntity<T> entity = new HttpEntity<>(payload, headers);
		return execute(uriBuilder.build().toUri(), HttpMethod.POST, entity, aClass);
	}

	public <T, R> ResponseEntity<R> patch(T payload, Class<R> aClass) {
		HttpEntity<T> entity = new HttpEntity<>(payload, headers);
		return execute(uriBuilder.build().toUri(), HttpMethod.PATCH, entity, aClass);
	}

	public <T> ResponseEntity<T> get(Class<T> aClass) {
		return execute(uriBuilder.build().toUri(), HttpMethod.GET, new HttpEntity<>(headers), aClass);
	}

	public ResponseEntity<Void> head() {
		return execute(uriBuilder.build().toUri(), HttpMethod.HEAD, new HttpEntity<>(headers), Void.class);
	}

	public <T> ResponseEntity<T> get(ParameterizedTypeReference<T> aClass) {
		return execute(uriBuilder.build().toUri(), HttpMethod.GET, new HttpEntity<>(headers), aClass);
	}

	public ResponseEntity delete() throws ApiFaultException{
		return httpRetryService != null
				? httpRetryService.doWithRetry(() -> restResource.delete(uriBuilder.build().toUri(), headers))
				: restResource.delete(uriBuilder.build().toUri(), headers);
	}

	private <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType){
		return httpRetryService != null
				? httpRetryService.doWithRetry(() -> restResource.execute(uri, httpMethod, entity, responseType))
				: restResource.execute(uri, httpMethod, entity, responseType);
	}

	private <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType) {
		return httpRetryService != null
				? httpRetryService.doWithRetry(() -> restResource.execute(uri, httpMethod, entity, responseType))
				: restResource.execute(uri, httpMethod, entity, responseType);
	}

	private void addMicrosoftTrackingHeaders() {
		headers.setMsRequestId(UUID.randomUUID().toString());
		headers.setMsCorrelationId(UUID.randomUUID().toString());
	}
	private void addMicrosoftTrackingHeaders(String msRequestId, String msCorrelationId) {
		headers.setMsRequestId(msRequestId);
		headers.setMsCorrelationId(msCorrelationId);
	}
}
