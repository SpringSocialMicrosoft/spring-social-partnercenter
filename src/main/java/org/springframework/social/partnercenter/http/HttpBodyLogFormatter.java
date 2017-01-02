package org.springframework.social.partnercenter.http;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.client.ClientHttpResponse;

public class HttpBodyLogFormatter {
	private final String ENCODING = "UTF-8";
	private Logger log = Logger.getLogger(HttpBodyLogFormatter.class.getName());

	String createBodyLogString(ClientHttpResponse body) {
		Optional<String> bodyAsString = getBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	String createBodyLogString(byte[] body) {
		Optional<String> bodyAsString = getBodyAsString(body);
		return String.format("Body : %n %s", bodyAsString.orElse("ERROR RETRIEVING BODY ..."));
	}

	private Optional<String> getBodyAsString(ClientHttpResponse response) {
		try {

			if(response.getBody() == null){
				return of("{}");
			}
			return of(convertStreamToString(response));
		} catch (Exception exception) {
				log.log(Level.FINER, exception.getMessage(), exception);
			return empty();
		}
	}

	private String convertStreamToString(ClientHttpResponse response) throws IOException {
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			inputStringBuilder.append('\n');
			line = bufferedReader.readLine();
		}
		response.getBody().reset();
		return inputStringBuilder.toString();
	}

	private Optional<String> getBodyAsString(byte[] body) {
		try {
			return of(new String(body, ENCODING));
		} catch (Exception exception) {
				log.log(Level.FINER, exception.getMessage(), exception);
			return empty();
		}
	}
}
