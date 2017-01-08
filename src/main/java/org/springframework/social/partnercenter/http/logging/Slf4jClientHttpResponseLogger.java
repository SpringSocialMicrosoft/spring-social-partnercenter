package org.springframework.social.partnercenter.http.logging;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class Slf4jClientHttpResponseLogger implements ClientHttpResponseLogger {
	private final HttpBodyLogFormatter httpBodyLogFormatter;
	private final HttpHeaderLogFormatter httpHeaderLogFormatter;
	private final Logger logger;

	public Slf4jClientHttpResponseLogger(Logger logger) {
		this.logger = logger;
		this.httpBodyLogFormatter = new HttpBodyLogFormatter();
		this.httpHeaderLogFormatter = new HttpHeaderLogFormatter();
	}

	public String formatLog(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException {
		StringBuilder buffer = new StringBuilder();
		buffer.append(String.format("Received HTTP response at %s response code  %s (%s) for %s request to URL : %s", dateTime.toString(), response.getStatusCode(), response.getStatusText(), request.getMethod(), request.getURI()));
		buffer.append(httpHeaderLogFormatter.formatHeaderLogs(response.getHeaders()));
		buffer.append(String.format("%n"));
		String bodyLogString = httpBodyLogFormatter.createBodyLogString(response);
		buffer.append(bodyLogString);
		return buffer.toString();
	}

	@Override
	public void logDebug(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException {
		logger.debug(formatLog(dateTime, request, response));
	}

	@Override
	public void logInfo(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException {
		logger.info(formatLog(dateTime, request, response));
	}

	@Override
	public void logWarning(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException {
		logger.warn(formatLog(dateTime, request, response));
	}

}
