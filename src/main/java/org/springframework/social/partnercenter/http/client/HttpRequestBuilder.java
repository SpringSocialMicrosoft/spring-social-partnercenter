package org.springframework.social.partnercenter.http.client;

import static java.util.Collections.singletonList;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpRequestBuilder {
	private HttpHeaders headers;
	private RestResource restResource;
	private UriComponentsBuilder uriBuilder;

	HttpRequestBuilder(RestResource restResource, String resourceBaseUri){
		this.headers = new HttpHeaders();
		this.restResource = restResource;
		this.uriBuilder = UriComponentsBuilder.fromUriString(resourceBaseUri);
	}
	public static HttpRequestBuilder createNew(RestResource service, String baseUri){
		return new HttpRequestBuilder(service, baseUri);
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

	public HttpRequestBuilder path(String path){
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

	public void delete(){
		restResource.delete(uriBuilder.build().toUri());
	}
}
