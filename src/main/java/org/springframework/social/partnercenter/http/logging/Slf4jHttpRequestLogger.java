package org.springframework.social.partnercenter.http.logging;

import static java.lang.String.format;

import java.time.Instant;

import org.slf4j.Logger;
import org.springframework.http.HttpRequest;

public class Slf4jHttpRequestLogger implements HttpRequestLogger {
	private final DefaultHttpHeaderLogFormatter httpHeaderLogFormatter;
	private final HttpBodyLogFormatter httpBodyLogFormatter;
	private final Logger logger;

	public Slf4jHttpRequestLogger(Logger logger, HttpBodyLogFormatter bodyLogFormatter) {
		this.logger = logger;
		this.httpHeaderLogFormatter = new DefaultHttpHeaderLogFormatter();
		this.httpBodyLogFormatter = bodyLogFormatter;
	}

	public String formatLog(Instant startTime, HttpRequest request, byte[] body){
		return format("HTTP %s Request sent to %s at %s: %s%s", request.getMethod(), request.getURI(), startTime.toString(),
				httpHeaderLogFormatter.formatHeaderLogs(request.getHeaders()),
				httpBodyLogFormatter.formatRequestLogs(body));
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

	@Override
	public void logError(Instant startTime, HttpRequest request, byte[] body){
		logger.error(formatLog(startTime, request, body));
	}
}
