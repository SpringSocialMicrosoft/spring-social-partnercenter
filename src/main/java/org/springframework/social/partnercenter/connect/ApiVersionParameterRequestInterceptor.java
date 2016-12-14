package org.springframework.social.partnercenter.connect;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

public class ApiVersionParameterRequestInterceptor implements ClientHttpRequestInterceptor {
	private final String parameterName;

	private final String apiVersion;

	/**
	 * Creates an instance of the interceptor, defaulting to use a parameter named "api-version".
	 * @param apiVersion The access token.
	 */
	public ApiVersionParameterRequestInterceptor(String apiVersion) {
		this(apiVersion, "api-version");
	}

	private ApiVersionParameterRequestInterceptor(String apiVersion, String parameterName) {
		this.apiVersion = apiVersion;
		this.parameterName = parameterName;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpRequestDecorator protectedResourceRequest = new HttpRequestDecorator(request);
		protectedResourceRequest.getHeaders().set(parameterName, apiVersion);
		return execution.execute(protectedResourceRequest, body);
	}
}
