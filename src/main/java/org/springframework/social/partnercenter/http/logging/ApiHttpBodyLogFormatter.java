package org.springframework.social.partnercenter.http.logging;

import static com.google.common.base.Charsets.UTF_8;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpResponse;

class ApiHttpBodyLogFormatter implements HttpBodyLogFormatter {
	public String formatResponseLogs(ClientHttpResponse body) {
		Optional<String> bodyAsString = getResponseBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	public String formatRequestLogs(byte[] body) {
		Optional<String> bodyAsString = getRequestBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	private Optional<String> getResponseBodyAsString(ClientHttpResponse response) {
		try {
			try (BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getBody()))) {
				return of(buffer.lines().collect(Collectors.joining("\n")));
			}
		} catch (IOException e) {
			return Optional.of("Could not read response body for logging");
		}
	}

	private Optional<String> getRequestBodyAsString(byte[] body) {
		try {
			return of(new String(body, UTF_8.displayName()));
		} catch (Exception exception) {
			return empty();
		}
	}
}
