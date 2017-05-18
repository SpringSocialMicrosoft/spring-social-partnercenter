package org.springframework.social.partnercenter.http.client;

import static java.util.Collections.singletonList;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.ApiFault;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.social.partnercenter.serialization.Json;
import org.springframework.social.partnercenter.serialization.JsonSerializationException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestResource {
	private RestTemplate restTemplate;
	private String resourceBaseUri;

	public RestResource(RestTemplate restTemplate, String resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	public HttpRequestBuilder request(){
		return new HttpRequestBuilder(this, this.resourceBaseUri);
	}
	public HttpRequestBuilder request(String msRequestId, String msCorrelationId){
		return new HttpRequestBuilder(this, this.resourceBaseUri, msRequestId, msCorrelationId);
	}

	public HttpRequestBuilder request(MediaType mediaType){
		return new HttpRequestBuilder(this, this.resourceBaseUri).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	public ResponseEntity delete(URI uri, HttpHeaders headers){
		try {
			return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
		} catch (HttpClientErrorException e) {
			throw buildApiFault(e);
		}
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType) {
		try{
			return restTemplate.exchange(uri, httpMethod, entity, responseType);
		} catch (HttpClientErrorException e){
			throw buildApiFault(e);
		}
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType) {
		try{
			return restTemplate.exchange(uri, httpMethod, entity, responseType);
		} catch (HttpClientErrorException e){
			throw buildApiFault(e);
		}
	}

	protected ApiFaultException buildApiFault(HttpClientErrorException e) {
		String responseBody = e.getResponseBodyAsString();
		try {
			ApiFault apiFault = Json.fromJson(responseBody, ApiFault.class);
			return new ApiFaultException(apiFault.getErrorMessage(), e, e.getStatusCode(), apiFault);
		} catch (JsonSerializationException serializationException) {
			ApiFault apiFault = new ApiFault();
			apiFault.setErrorMessage(responseBody);
			return new ApiFaultException(responseBody, e, e.getStatusCode(), apiFault);
		}
	}
}
