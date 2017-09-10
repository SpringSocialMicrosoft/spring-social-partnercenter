package org.springframework.social.partnercenter.http.logging;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.http.HttpStatus;

public class HttpRequestResponseLoggerFactory {

	public static HttpRequestResponseLogger createSlf4jApiLogger(Class<?> logSource, LogLevel logLevel, HttpStatus... expectedStatus){
		return createSlf4jApiLogger(logSource, logLevel, asList(expectedStatus));
	}
	public static HttpRequestResponseLogger createSlf4jApiLogger(Class<?> logSource, LogLevel logLevel, List<HttpStatus> expectedStatus){
		return new Slf4jHttpRequestResponseLogger(logSource, logLevel, expectedStatus);
	}
	public static HttpRequestResponseLogger createSlf4jAuthorizationLogger(Class<?> logSource, LogLevel logLevel, HttpStatus... expectedStatus){
		return new Slf4jHttpRequestResponseLogger(logSource, logLevel, new AuthorizationHttpBodyLogFormatter(), asList(expectedStatus));
	}
}
