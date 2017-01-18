package org.springframework.social.partnercenter.http.client;

import static java.util.Collections.singletonList;

import java.net.URI;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestResource {
	private RestTemplate restTemplate;
	private String resourceBaseUri;

	public HttpRequestBuilder request(){
		return new HttpRequestBuilder(this, this.resourceBaseUri);
	}

	public HttpRequestBuilder request(String msRequestId, String msCorrelationId){
		return new HttpRequestBuilder(this, this.resourceBaseUri, msRequestId, msCorrelationId);
	}
	public HttpRequestBuilder request(MediaType mediaType){
		return new HttpRequestBuilder(this, this.resourceBaseUri).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public RestResource(RestTemplate restTemplate, String resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	<T> ResponseEntity<T> get(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}

	<T> ResponseEntity<T> get(URI url, Class<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}

	<T> ResponseEntity<T> get(URI url, Class<T> responseType, Map<String, String> header) {
		return execute(url, HttpMethod.GET, new HttpEntity<>(header), responseType);
	}

	<T> ResponseEntity<T> post(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.POST, HttpEntity.EMPTY, responseType);
	}

	<T> ResponseEntity<T> post(URI url, ParameterizedTypeReference<T> responseType, Map<String, String> headers) {
		return execute(url, HttpMethod.POST, new HttpEntity<Object>(headers), responseType);
	}

	<T, R> ResponseEntity<R> post(URI uri, T entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, new HttpEntity<>(entity), responseType);
	}

	<T, R> ResponseEntity<R> post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, entity, responseType);
	}

	<T, R> ResponseEntity<R> patch(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PATCH, entity, responseType);
	}

	<T, R> ResponseEntity<R> put(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PUT, entity, responseType);
	}

	public ResponseEntity delete(URI uri, HttpEntity entity){
		return restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType){
		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		);
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType){
		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		);
	}

}
