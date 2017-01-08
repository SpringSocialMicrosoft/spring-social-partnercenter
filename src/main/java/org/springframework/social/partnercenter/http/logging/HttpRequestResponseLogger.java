package org.springframework.social.partnercenter.http.logging;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public interface HttpRequestResponseLogger {
	void log(Instant startTime, Instant endTime, HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException;
}
