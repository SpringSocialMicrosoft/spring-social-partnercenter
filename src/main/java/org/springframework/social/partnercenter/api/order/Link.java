package org.springframework.social.partnercenter.api.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link {
	private String uri;
	private String method;
	private List<String> headers;

	public String getUri() {
		return uri;
	}

	public Link setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public Link setMethod(String method) {
		this.method = method;
		return this;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public Link setHeaders(List<String> headers) {
		this.headers = headers;
		return this;
	}
}
