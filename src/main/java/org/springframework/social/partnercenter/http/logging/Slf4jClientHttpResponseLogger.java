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

	public Slf4jClientHttpResponseLogger(Logger logger, HttpBodyLogFormatter bodyLogFormatter) {
		this.logger = logger;
		this.httpBodyLogFormatter = bodyLogFormatter;
		this.httpHeaderLogFormatter = new DefaultHttpHeaderLogFormatter();
	}

	public String formatLog(Instant dateTime, HttpRequest request, ClientHttpResponse response) {
		StringBuilder buffer = new StringBuilder();
		try {
			buffer.append(String.format("Received HTTP response at %s response code  %s (%s) for %s request to URL : %s", dateTime.toString(), response.getStatusCode(), response.getStatusText(), request.getMethod(), request.getURI()));
		} catch (IOException e) {
			logger.error("Could not log response url. Continuing to log whatever possible.");
		}
		buffer.append(httpHeaderLogFormatter.formatHeaderLogs(response.getHeaders()));
		buffer.append(String.format("%n"));
		String bodyLogString = httpBodyLogFormatter.formatResponseLogs(response);
		buffer.append(bodyLogString);
		return buffer.toString();
	}

	@Override
	public void logDebug(Instant dateTime, HttpRequest request, ClientHttpResponse response) {
		logger.debug(formatLog(dateTime, request, response));
	}

	@Override
	public void logInfo(Instant dateTime, HttpRequest request, ClientHttpResponse response) {
		logger.info(formatLog(dateTime, request, response));
	}

	@Override
	public void logWarning(Instant dateTime, HttpRequest request, ClientHttpResponse response) {
		logger.warn(formatLog(dateTime, request, response));
	}

	@Override
	public void logError(Instant dateTime, HttpRequest request, ClientHttpResponse response) {
		logger.error(formatLog(dateTime, request, response));
	}
}
