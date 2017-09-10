package org.springframework.social.partnercenter.http.logging;

import static com.google.common.base.Charsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.social.partnercenter.serialization.Json.toJson;
import static org.springframework.social.partnercenter.serialization.Json.toJsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthorizationHttpBodyLogFormatter implements HttpBodyLogFormatter {

	public String formatRequestLogs(byte[] body) {
		Optional<String> bodyAsString = getRequestBodyAsString(body).map(this::obfuscateSensitiveFieldsInRequest);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	public String formatResponseLogs(ClientHttpResponse body) {

		Optional<String> bodyAsString = getResponseBodyAsString(body).map(this::obfuscateSensitiveFieldsInResponse);

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

	private String obfuscateSensitiveFieldsInRequest(String bodyString) {
		if (!bodyString.contains("grant_type")) {
			return bodyString;
		}
		final String[] keyValuePairs = bodyString.split("&");
		StringBuilder newBody = new StringBuilder();
		for (int i = 0; i != keyValuePairs.length; i++) {
			String key = keyValuePairs[i].split("=")[0];
			if (asList("client_id", "client_secret", "password").contains(key)) {
				newBody.append(key.concat("=*&"));
			}
			else newBody.append(keyValuePairs[i].concat("&"));
		}
		return newBody.toString();
	}

	private String obfuscateSensitiveFieldsInResponse(String bodyString) {
		if (!bodyString.contains("access_token")) {
			return bodyString;
		}
		try {
			final JsonNode jsonNode = toJsonNode(bodyString);
			((ObjectNode) jsonNode).put("access_token", "*");
			((ObjectNode) jsonNode).put("refresh_token", "*");
			((ObjectNode) jsonNode).put("id_token", "*");
			return toJson(jsonNode);
		} catch (Exception e) {
			return "Could not parse obfuscate sensitive fields. Body will not be written to logs.";
		}
	}
}
