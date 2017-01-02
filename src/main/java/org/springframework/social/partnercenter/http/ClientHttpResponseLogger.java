package org.springframework.social.partnercenter.http;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class ClientHttpResponseLogger {
	private final HttpBodyLogFormatter httpBodyLogFormatter;
	private final HttpHeaderLogFormatter httpHeaderLogFormatter;

	public ClientHttpResponseLogger() {
		this.httpBodyLogFormatter = new HttpBodyLogFormatter();
		this.httpHeaderLogFormatter = new HttpHeaderLogFormatter();
	}

	public String formatLog(HttpRequest request, ClientHttpResponse response) throws IOException {
		StringBuilder buffer = new StringBuilder();
		buffer.append(String.format("HttpResponse: Method=%s, Url=%s ", request.getMethod(), request.getURI()));
		buffer.append(String.format("Received response code  %s (%s) for %s request to URL : %s", response.getStatusCode(), response.getStatusText(), request.getMethod(), request.getURI()));
		buffer.append(httpHeaderLogFormatter.formatHeaderLogs(response.getHeaders()));
		buffer.append(String.format("%n"));
		String bodyLogString = httpBodyLogFormatter.createBodyLogString(response);
		buffer.append(bodyLogString);
		return buffer.toString();
	}

	public void logWarning(Logger logger, HttpRequest request, ClientHttpResponse response) throws IOException {
		logger.warning(formatLog(request, response));
	}
}
