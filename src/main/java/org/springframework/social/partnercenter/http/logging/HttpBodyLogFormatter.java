package org.springframework.social.partnercenter.http.logging;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.StringWriter;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

public class HttpBodyLogFormatter {
	private final String ENCODING = "UTF-8";

	String createBodyLogString(ClientHttpResponse body) {
		Optional<String> bodyAsString = getBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	String createBodyLogString(byte[] body) {
		Optional<String> bodyAsString = getBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	private Optional<String> getBodyAsString(ClientHttpResponse response) {
		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(response.getBody(), writer, ENCODING);
		} catch (Exception exception) {
			return empty();
		}
		return of(writer.toString());
	}


	private Optional<String> getBodyAsString(byte[] body) {
		try {
			return of(new String(body, ENCODING));
		} catch (Exception exception) {
			return empty();
		}
	}
}
