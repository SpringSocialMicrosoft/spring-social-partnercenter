package org.springframework.social.partnercenter.http.logging;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpResponse;

class HttpBodyLogFormatter {
	private final String ENCODING = "UTF-8";

	String createResponseBodyLogString(ClientHttpResponse body) throws IOException {
		Optional<String> bodyAsString = getResponseBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	String createRequestBodyLogString(byte[] body) {
		Optional<String> bodyAsString = getRequestBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	private Optional<String> getResponseBodyAsString(ClientHttpResponse response) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getBody()))) {
			return of(buffer.lines().collect(Collectors.joining("\n")));
		}
	}

	private Optional<String> getRequestBodyAsString(byte[] body) {
		try {
			return of(new String(body, ENCODING));
		} catch (Exception exception) {
			return empty();
		}
	}
}
