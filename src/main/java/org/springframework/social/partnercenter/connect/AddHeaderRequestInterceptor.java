package org.springframework.social.partnercenter.connect;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

public class AddHeaderRequestInterceptor implements ClientHttpRequestInterceptor {
	private final String headerName;

	private final String headerValue;

	/**
	 * Creates an instance of the interceptor, that inserts a header with given name and value.
	 * @param headerName The name of the header
	 * @param headerValue The value of the header.
	 */
	public AddHeaderRequestInterceptor(String headerName, String headerValue) {
		this.headerValue = headerValue;
		this.headerName = headerName;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpRequest protectedResourceRequest = new HttpRequestDecorator(request);
		protectedResourceRequest.getHeaders().set(headerName, headerValue);
		return execution.execute(protectedResourceRequest, body);
	}
}
