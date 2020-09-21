package org.springframework.social.partnercenter.http.logging;

import java.time.Instant;

import org.springframework.http.HttpRequest;

public interface HttpRequestLogger {
	void logDedug(Instant startTime, HttpRequest request, byte[] body);

	void logInfo(Instant startTime, HttpRequest request, byte[] body);

	void logWarning(Instant startTime, HttpRequest request, byte[] body);

	void logError(Instant startTime, HttpRequest request, byte[] body);
}
