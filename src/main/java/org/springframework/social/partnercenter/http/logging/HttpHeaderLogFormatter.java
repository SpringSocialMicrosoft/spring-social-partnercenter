package org.springframework.social.partnercenter.http.logging;

import static java.util.Collections.singletonList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.social.partnercenter.serialization.Json;

public class HttpHeaderLogFormatter {
	String formatHeaderLogs(HttpHeaders headers){
		StringBuilder log = new StringBuilder();
		log.append(String.format("%nHTTP headers : "));
		Map<String, List<String>> headersByName = new HashMap<>();
		for (String headerName : headers.keySet()) {
			List<String> headerValue = headers.get(headerName);
			if ( headerName == "Authorization"){
				headersByName.put(headerName, singletonList("*"));
			}
			else {
				headersByName.put(headerName, headerValue);
			}
		}
		log.append(Json.toJson(headersByName));
		log.append(String.format("%n"));
		return log.toString();
	}
}
