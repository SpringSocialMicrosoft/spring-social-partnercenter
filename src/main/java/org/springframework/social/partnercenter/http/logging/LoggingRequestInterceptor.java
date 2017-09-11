package org.springframework.social.partnercenter.http.logging;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final List<HttpStatus> DEFAULT_EXPECTED_HTTP_STATUS = asList(OK, CREATED, ACCEPTED, NO_CONTENT);
	private final HttpRequestResponseLogger logger;

	public LoggingRequestInterceptor(HttpRequestResponseLogger httpRequestResponseLogger){
		this.logger = httpRequestResponseLogger;
	}

	public LoggingRequestInterceptor(Class<?> logSource, LogLevel logLevel, List<HttpStatus> expectedStatus) {
		this.logger = HttpRequestResponseLoggerFactory.createSlf4jApiLogger(logSource, logLevel, expectedStatus);
	}

	public LoggingRequestInterceptor(Class<?> logSource, LogLevel level) {
		this.logger = HttpRequestResponseLoggerFactory.createSlf4jApiLogger(logSource, level, DEFAULT_EXPECTED_HTTP_STATUS);
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
