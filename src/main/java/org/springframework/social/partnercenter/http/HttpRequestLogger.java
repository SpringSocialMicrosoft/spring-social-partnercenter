package org.springframework.social.partnercenter.http;

import java.util.logging.Logger;

import org.springframework.http.HttpRequest;

public class HttpRequestLogger {
	private final HttpHeaderLogFormatter httpHeaderLogFormatter;
	private final HttpBodyLogFormatter httpBodyLogFormatter;

	public HttpRequestLogger() {
		this.httpHeaderLogFormatter = new HttpHeaderLogFormatter();
		this.httpBodyLogFormatter = new HttpBodyLogFormatter();
	}

	public String formatLog(HttpRequest request, byte[] body){
		StringBuilder buffer = new StringBuilder();
		buffer.append(String.format("HttpRequest: Method=%s, Url=%s ", request.getMethod(), request.getURI()));
		buffer.append(httpHeaderLogFormatter.formatHeaderLogs(request.getHeaders()));
		buffer.append(httpBodyLogFormatter.createBodyLogString(body));
		return buffer.toString();
	}
	public void logWarning(Logger logger, HttpRequest request, byte[] body){
		logger.warning(formatLog(request, body));
	}
}
