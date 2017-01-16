package org.springframework.social.partnercenter.http.client;

import static java.util.Collections.singletonList;

import java.net.URI;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestResource {
	private RestTemplate restTemplate;
	private String resourceBaseUri;

	public HttpRequestBuilder request(){
		return new HttpRequestBuilder(this, this.resourceBaseUri);
	}
	public HttpRequestBuilder request(MediaType mediaType){
		return new HttpRequestBuilder(this, this.resourceBaseUri).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public RestResource(RestTemplate restTemplate, String resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	public <T> T get(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}

	public <T> T get(URI url, Class<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}

	private  <T> T get(URI url, Class<T> responseType, Map<String, String> header) {
		return execute(url, HttpMethod.GET, new HttpEntity<>(header), responseType);
	}

	public <T> T post(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.POST, HttpEntity.EMPTY, responseType);
	}

	public <T> T post(URI url, ParameterizedTypeReference<T> responseType, Map<String, String> headers) {
		return execute(url, HttpMethod.POST, new HttpEntity<Object>(headers), responseType);
	}

	public <T, R> R post(URI uri, T entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, new HttpEntity<>(entity), responseType);
	}

	public <T, R> R post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, entity, responseType);
	}

	public <T, R> R patch(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PATCH, entity, responseType);
	}

	public <T, R> R put(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PUT, entity, responseType);
	}

	public void delete(URI uri){
		restTemplate.delete(uri);
	}

	public <T, R> R execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType){

		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		).getBody();
	}

	public <T, R> R execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType){
		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		).getBody();
	}

}
