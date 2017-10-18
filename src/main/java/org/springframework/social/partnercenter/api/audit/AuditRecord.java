package org.springframework.social.partnercenter.api.audit;

import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditRecord {
	private String partnerId;
	private String customerId;
	private String customerName;
	private String userPrincipalName;
	private String applicationId;
	private ResourceType resourceType;
	private String resourceNewValue;
	private OperationType operationType;
	private ZonedDateTime operationDate;
	private OperationStatus operationStatus;
	private String originalCorrelationId;
	private String sessionId;
	private Map<String, String> attributes;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceNewValue() {
		return resourceNewValue;
	}

	public void setResourceNewValue(String resourceNewValue) {
		this.resourceNewValue = resourceNewValue;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public ZonedDateTime getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(ZonedDateTime operationDate) {
		this.operationDate = operationDate;
	}

	public OperationStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getOriginalCorrelationId() {
		return originalCorrelationId;
	}

	public void setOriginalCorrelationId(String originalCorrelationId) {
		this.originalCorrelationId = originalCorrelationId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public AuditRecord partnerId(String partnerId) {
		this.partnerId = partnerId;
		return this;
	}
}
