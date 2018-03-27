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
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

public class RestClient implements RestResource {
	private RestOperations restTemplate;
	private URI resourceBaseUri;

	public RestClient(RestOperations restTemplate, URI resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	@Override
	public HttpRequestBuilder request(){
		return new HttpRequestBuilder(this, UriComponentsBuilder.fromUri(resourceBaseUri));
	}

	@Override
	public HttpRequestBuilder request(String msRequestId, String msCorrelationId){
		return new HttpRequestBuilder(this, UriComponentsBuilder.fromUri(resourceBaseUri), msRequestId, msCorrelationId);
	}

	@Override
	public HttpRequestBuilder request(MediaType mediaType){
		return new HttpRequestBuilder(this, UriComponentsBuilder.fromUri(resourceBaseUri)).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	@Override
	public HttpRequestBuilder request(URI baseUri) {
		return new HttpRequestBuilder(this, UriComponentsBuilder.fromUri(baseUri));
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
