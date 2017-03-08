package org.springframework.social.partnercenter.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationFault {
	@JsonProperty("error")
	private String error;
	@JsonProperty("error_description")
	private String errorDescription;
	@JsonProperty("error_codes")
	private List<Integer> errorCodes;
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonProperty("trace_id")
	private String traceId;
	@JsonProperty("correlation_id")
	private String correlationId;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public List<Integer> getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(List<Integer> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
