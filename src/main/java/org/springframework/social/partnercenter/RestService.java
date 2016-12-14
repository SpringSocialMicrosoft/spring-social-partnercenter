package org.springframework.social.partnercenter;

import static org.springframework.social.partnercenter.RestResponse.createResponse;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestService {
	private RestTemplate restTemplate;

	public RequestBuilder newRequest(){
		return new RequestBuilder(this);
	}
	public RestService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public <T> RestResponse<T> get(URI url, ParameterizedTypeReference<T> responseType) {
		return createResponse(restTemplate.exchange(url, HttpMethod.GET, null, responseType));
	}

	public <T> RestResponse<T> get(URI url, Class<T> responseType) {
		ResponseEntity<T> body = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
		return createResponse(body);

	}

	private  <T> RestResponse<T> get(URI url, Class<T> responseType, Map<String, String> header) {
		ResponseEntity<T> body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(header), responseType);
		return createResponse(body);

	}

	public <T> RestResponse<T> post(URI url, ParameterizedTypeReference<T> responseType) {
		return createResponse(restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, responseType));
	}

	public <T> RestResponse<T> post(URI url, ParameterizedTypeReference<T> responseType, Map<String, String> headers) {
		return createResponse(restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers), responseType));
	}

	public <T, R> RestResponse<R> post(URI uri, T entity, Class<R> responseType) {
		return createResponse(restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(entity),
				responseType));
	}

	public <T, R> RestResponse<R> post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return createResponse(restTemplate.exchange(
				uri,
				HttpMethod.POST,
				entity,
				responseType));
	}

	public <T, R> RestResponse<R> put(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		ResponseEntity<R> exchange = restTemplate.exchange(
				uri,
				HttpMethod.PUT,
				entity,
				responseType);
		return createResponse(exchange);
	}

	public void delete(URI uri){
		restTemplate.delete(uri);
	}

	public static class RequestBuilder{
		private HttpHeaders headers;
		private RestService restService;

		private RequestBuilder(RestService restService){
			this.headers = new HttpHeaders();
			this.restService = restService;
		}
		public static RequestBuilder createNew(RestService service){
			return new RequestBuilder(service);
		}

		public RequestBuilder putHeader(String key, String value){
			headers.put(key, Collections.singletonList(value));
			return this;
		}

		public RequestBuilder setHeader(String key, String value){
			headers.set(key, value);
			return this;
		}

		public <T, R> R put(URI uri, T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restService.put(uri, tHttpEntity, aClass).getBody();
		}

		public <T, R> R post(URI uri, T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restService.post(uri, tHttpEntity, aClass).getBody();
		}
	}
}
