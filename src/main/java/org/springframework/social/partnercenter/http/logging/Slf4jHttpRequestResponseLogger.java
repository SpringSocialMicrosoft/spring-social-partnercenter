package org.springframework.social.partnercenter.http.logging;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

public class Slf4jHttpRequestResponseLogger implements HttpRequestResponseLogger {
	private final Logger logger;
	private final LogLevel logLevel;
	private final HttpRequestLogger requestLogger;
	private final ClientHttpResponseLogger responseLogger;
	private final List<HttpStatus> expectedStatus;

	Slf4jHttpRequestResponseLogger(Class<?> logSource, LogLevel logLevel, List<HttpStatus> expectedStatus) {
		this.logger = LoggerFactory.getLogger(logSource);
		this.logLevel  = logLevel;
		this.requestLogger = new Slf4jHttpRequestLogger(logger, new ApiHttpBodyLogFormatter());
		this.responseLogger = new Slf4jClientHttpResponseLogger(logger, new ApiHttpBodyLogFormatter());
		this.expectedStatus = expectedStatus;
	}

	Slf4jHttpRequestResponseLogger(Class<?> logSource, LogLevel logLevel, HttpBodyLogFormatter bodyLogFormatter, List<HttpStatus> expectedStatus) {
		this.logger = LoggerFactory.getLogger(logSource);
		this.logLevel  = logLevel;
		this.requestLogger = new Slf4jHttpRequestLogger(logger, bodyLogFormatter);
		this.responseLogger = new Slf4jClientHttpResponseLogger(logger, bodyLogFormatter);
		this.expectedStatus = expectedStatus;
	}

	@Override
	public void log(Instant startTime, Instant endTime, HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
		if (expectedStatus.contains(response.getStatusCode())){
			if(logger.isDebugEnabled() && logLevel.equals(LogLevel.DEBUG)){
				requestLogger.logDedug(startTime, request, body);
				responseLogger.logDebug(endTime, request, response);
			} else if (logger.isInfoEnabled() && logLevel.equals(LogLevel.INFO)){
				requestLogger.logInfo(startTime, request, body);
				responseLogger.logInfo(endTime, request, response);
			} else if (logger.isWarnEnabled() && logLevel.equals(LogLevel.WARN)){
				requestLogger.logWarning(startTime, request, body);
				responseLogger.logWarning(endTime, request, response);
			}
		} else {
			requestLogger.logError(startTime, request, body);
			responseLogger.logError(endTime, request, response);
		}
	}
}
