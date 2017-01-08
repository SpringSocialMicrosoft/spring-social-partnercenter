package org.springframework.social.partnercenter.http.logging;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public interface ClientHttpResponseLogger {
	void logDebug(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException;

	void logInfo(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException;

	void logWarning(Instant dateTime, HttpRequest request, ClientHttpResponse response) throws IOException;
}
