package org.springframework.social.partnercenter.http.client;

import org.springframework.http.MediaType;

public interface RestResource {
	HttpRequestBuilder request();
	HttpRequestBuilder request(String msRequestId, String msCorrelationId);
	HttpRequestBuilder request(MediaType mediaType);
}
