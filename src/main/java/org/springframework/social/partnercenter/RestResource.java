package org.springframework.social.partnercenter;

import static java.util.Collections.singletonList;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestResource {
	private RestTemplate restTemplate;
	private String resourceBaseUri;

	public RequestBuilder request(){
		return new RequestBuilder(this);
	}
	public RequestBuilder request(MediaType mediaType){
		return new RequestBuilder(this).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	public RestResource(RestTemplate restTemplate, String resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	public <T> T get(URI url, ParameterizedTypeReference<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
	}

	public <T> T get(URI url, Class<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
	}

	private  <T> T get(URI url, Class<T> responseType, Map<String, String> header) {
		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(header), responseType).getBody();
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

	public <T, R> R post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return restTemplate.exchange(
				uri,
				HttpMethod.POST,
				entity,
				responseType).getBody();
	}

	public <T, R> R patch(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return restTemplate.exchange(
				uri,
				HttpMethod.PATCH,
				entity,
				responseType).getBody();
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
		private RestResource restResource;
		private UriComponentsBuilder uriBuilder;

		private RequestBuilder(RestResource restResource){
			this.headers = new HttpHeaders();
			this.restResource = restResource;
			this.uriBuilder = UriComponentsBuilder.fromUriString(restResource.resourceBaseUri);
		}
		public static RequestBuilder createNew(RestResource service){
			return new RequestBuilder(service);
		}

		public RequestBuilder header(String key, List<String> value){
			headers.put(key, value);
			return this;
		}

		public RequestBuilder header(String key, String value){
			headers.put(key, singletonList(value));
			return this;
		}

		public RequestBuilder pathSegment(String ... pathSegments){
			uriBuilder.pathSegment(pathSegments);
			return this;
		}

		public RequestBuilder queryParam(String name, Object... values){
			uriBuilder.queryParam(name, values);
			return this;
		}

		public RequestBuilder path(String path){
			uriBuilder.path(path);
			return this;
		}

		public <T, R> R put(T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restResource.put(uriBuilder.build().toUri(), tHttpEntity, aClass);
		}

		public <T, R> R post(T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restResource.post(uriBuilder.build().toUri(), tHttpEntity, aClass);
		}

		public <T, R> R patch(T payload, Class<R> aClass){
			HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
			return restResource.patch(uriBuilder.build().toUri(), tHttpEntity, aClass);
		}

		public <T> T get(Class<T> aClass){
			return restResource.get(uriBuilder.build().toUri(), aClass);
		}

		public <T> T get(ParameterizedTypeReference<T> aClass){
			return restResource.get(uriBuilder.build().toUri(), aClass);
		}
	}
}
