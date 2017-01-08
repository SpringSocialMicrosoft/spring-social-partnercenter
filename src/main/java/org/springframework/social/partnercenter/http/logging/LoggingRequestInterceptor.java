package org.springframework.social.partnercenter.http.logging;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final List<HttpStatus> DEFAULT_EXPECTED_HTTP_STATUS = asList(HttpStatus.OK, HttpStatus.NOT_FOUND);
	private final HttpRequestResponseLogger logger;

	public LoggingRequestInterceptor(HttpRequestResponseLogger httpRequestResponseLogger){
		this.logger = httpRequestResponseLogger;
	}

	public LoggingRequestInterceptor(Class<?> logSource, LogLevel logLevel, List<HttpStatus> expectedStatus) {
		this.logger = new Slf4jHttpRequestResponseLogger(logSource, logLevel, expectedStatus);
	}

	public LoggingRequestInterceptor(Class<?> logSource, LogLevel level) {
		this.logger = new Slf4jHttpRequestResponseLogger(logSource, level, DEFAULT_EXPECTED_HTTP_STATUS);
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		Instant startTime = Instant.now();
		ClientHttpResponse response = execution.execute(request, body);
		Instant endTime = Instant.now();
		logger.log(startTime, endTime, request, body, response);
		return response;
	}
}
