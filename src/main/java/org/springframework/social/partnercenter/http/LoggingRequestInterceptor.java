package org.springframework.social.partnercenter.http;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
	private final HttpRequestLogger requestLogger;
	private final ClientHttpResponseLogger responseLogger;
	private final Logger logger;
	private final Collection<HttpStatus> expectedStatus;
	private static Collection<HttpStatus> DEFAULT_EXPECTED_HTTP_STATUS = asList(HttpStatus.OK, HttpStatus.NOT_FOUND);

	public LoggingRequestInterceptor(Logger logger) {
		this(logger, DEFAULT_EXPECTED_HTTP_STATUS);
	}
	public LoggingRequestInterceptor(Logger logger, Collection<HttpStatus> expectedStatus) {
		this.requestLogger = new HttpRequestLogger();
		this.responseLogger = new ClientHttpResponseLogger();
		this.logger = logger;
		this.expectedStatus = expectedStatus;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		logResponse(request, response);
		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		if(logger.isLoggable(Level.FINER)){
			logger.log(Level.FINER, requestLogger.formatLog(request, body));
		}
		else {
			requestLogger.logWarning(logger, request, body);
		}
	}

	private void logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
		if(expectedStatus.contains(response.getStatusCode()) && logger.isLoggable(Level.FINER)){
			logger.log(Level.FINER, responseLogger.formatLog(request, response));
		}
		else {
			responseLogger.logWarning(logger, request, response);
		}
	}
}
