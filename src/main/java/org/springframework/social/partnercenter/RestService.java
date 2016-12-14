package org.springframework.social.partnercenter;

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

	public <T> T get(URI url, ParameterizedTypeReference<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
	}

	public <T> T get(URI url, Class<T> responseType) {
		ResponseEntity<T> body = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
		return body.getBody();

	}

	private  <T> T get(URI url, Class<T> responseType, Map<String, String> header) {
		ResponseEntity<T> body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(header), responseType);
		return body.getBody();

	}

	public <T> T post(URI url, ParameterizedTypeReference<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, responseType).getBody();
	}

	public <T> T post(URI url, ParameterizedTypeReference<T> responseType, Map<String, String> headers) {
		return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers), responseType).getBody();
	}

	public <T, R> R post(URI uri, T entity, Class<R> responseType) {
		return restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(entity),
				responseType).getBody();
	}

	public <T, R> ResponseEntity<R> post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return restTemplate.exchange(
				uri,
				HttpMethod.POST,
				entity,
				responseType);
	}

	public <T, R> R put(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return restTemplate.exchange(
				uri,
				HttpMethod.PUT,
				entity,
				responseType).getBody();
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
			return restService.put(uri, tHttpEntity, aClass);
		}

		public <T, R> ResponseEntity<R> post(URI uri, T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restService.post(uri, tHttpEntity, aClass);
		}
	}
}
