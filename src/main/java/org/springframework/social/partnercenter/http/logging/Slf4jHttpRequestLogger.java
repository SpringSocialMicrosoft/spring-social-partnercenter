package org.springframework.social.partnercenter.http.logging;

import static java.lang.String.format;

import java.time.Instant;

import org.slf4j.Logger;
import org.springframework.http.HttpRequest;

public class Slf4jHttpRequestLogger implements HttpRequestLogger {
	private final HttpHeaderLogFormatter httpHeaderLogFormatter;
	private final HttpBodyLogFormatter httpBodyLogFormatter;
	private final Logger logger;

	public Slf4jHttpRequestLogger(Logger logger) {
		this.logger = logger;
		this.httpHeaderLogFormatter = new HttpHeaderLogFormatter();
		this.httpBodyLogFormatter = new HttpBodyLogFormatter();
	}

	public String formatLog(Instant startTime, HttpRequest request, byte[] body){
		return format("HTTP Request sent at %s: %s%s", startTime.toString(),
				httpHeaderLogFormatter.formatHeaderLogs(request.getHeaders()),
				httpBodyLogFormatter.createBodyLogString(body));
	}

	@Override
	public void logDedug(Instant startTime, HttpRequest request, byte[] body){
		logger.debug(formatLog(startTime, request, body));
	}

	@Override
	public void logInfo(Instant startTime, HttpRequest request, byte[] body){
		logger.info(formatLog(startTime, request, body));
	}

	@Override
	public void logWarning(Instant startTime, HttpRequest request, byte[] body){
		logger.warn(formatLog(startTime, request, body));
	}
}
