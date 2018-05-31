package org.springframework.social.partnercenter.http.logging;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.social.partnercenter.serialization.Json.toJson;
import static org.springframework.social.partnercenter.serialization.Json.toJsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class AuthorizationHttpBodyLogFormatter implements HttpBodyLogFormatter {

	public String formatRequestLogs(byte[] body) {
		Optional<String> bodyAsString = getRequestBodyAsString(body).map(this::obfuscateSensitiveFieldsInRequest);
		return String.format("Body:\n\t%s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	public String formatResponseLogs(ClientHttpResponse body) {

		Optional<String> bodyAsString = getResponseBodyAsString(body).map(this::obfuscateSensitiveFieldsInResponse);

		return String.format("Body:\n\t%s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
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
			return of(new String(body, UTF_8.name()));
		} catch (UnsupportedEncodingException exception) {
			return empty();
		}
	}

	private String obfuscateSensitiveFieldsInRequest(String bodyString) {
		final List<NameValuePair> obfuscatedBody = URLEncodedUtils.parse(bodyString, UTF_8).stream().map(pair -> {
			if (asList("client_secret", "password", "refresh_token").contains(pair.getName())) {
				return new BasicNameValuePair(pair.getName(), "*");
			}
			return pair;
		}).collect(Collectors.toList());
		return URLEncodedUtils.format(obfuscatedBody, UTF_8);
	}

	private String obfuscateSensitiveFieldsInResponse(String bodyString) {
		if (!bodyString.contains("access_token")) {
			return bodyString;
		}
		try {
			final ObjectNode jsonNode = (ObjectNode) toJsonNode(bodyString);
			jsonNode.fields().forEachRemaining(element -> {
				if (element.getKey().contains("_token")){
					jsonNode.put(element.getKey(), "*");
				}
			});
			return toJson(jsonNode);
		} catch (Exception e) {
			return "Could not parse obfuscate sensitive fields. Body will not be written to logs.";
		}
	}
}
