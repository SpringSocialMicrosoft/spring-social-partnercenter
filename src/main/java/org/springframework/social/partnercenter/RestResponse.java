package org.springframework.social.partnercenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class RestResponse<T> {
	private T body;
	private int HttpStatusCode;
	private String HttStatusMessage;
	private Map<String, List<String>> headers;

	public static <T> RestResponse<T> createResponse(ResponseEntity<T> responseEntity){
		return ResponseBuilder.build(responseEntity);
	}
	public T getBody() {
		return body;
	}

	public RestResponse setBody(T body) {
		this.body = body;
		return this;
	}

	public int getHttpStatusCode() {
		return HttpStatusCode;
	}

	public RestResponse setHttpStatusCode(int httpStatusCode) {
		HttpStatusCode = httpStatusCode;
		return this;
	}

	public String getHttStatusMessage() {
		return HttStatusMessage;
	}

	public RestResponse setHttStatusMessage(String httStatusMessage) {
		HttStatusMessage = httStatusMessage;
		return this;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public RestResponse setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
		return this;
	}

	public static class ResponseBuilder{
		public static <T> RestResponse<T> build(ResponseEntity<T> responseEntity) {
			RestResponse<T> restResponse = new RestResponse<>();
			restResponse.setBody(responseEntity.getBody());
			restResponse.setHeaders(new HashMap<>());
			restResponse.setHttpStatusCode(responseEntity.getStatusCode().value());
			restResponse.setHttStatusMessage(responseEntity.getStatusCode().getReasonPhrase());
			responseEntity.getHeaders().keySet().forEach(key -> restResponse.getHeaders().put(key, responseEntity.getHeaders().get(key)));
			return restResponse;
		}
	}
}
