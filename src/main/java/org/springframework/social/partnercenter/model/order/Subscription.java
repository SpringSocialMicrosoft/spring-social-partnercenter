package org.springframework.social.partnercenter.model.order;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {
	private String id;
	private String friendlyName;
	private String quantity;
	private String creationDate;
	private String effectiveStartDate;
	private String commitmentEndDate;
	private String status;
	private String billingType;
	private String contractType;
	private boolean autoRenewEnabled;
	private String orderId;
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public Subscription setId(String id) {
		this.id = id;
		return this;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public Subscription setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public String getQuantity() {
		return quantity;
	}

	public Subscription setQuantity(String quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Subscription setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public Subscription setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
		return this;
	}

	public String getCommitmentEndDate() {
		return commitmentEndDate;
	}

	public Subscription setCommitmentEndDate(String commitmentEndDate) {
		this.commitmentEndDate = commitmentEndDate;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Subscription setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getBillingType() {
		return billingType;
	}

	public Subscription setBillingType(String billingType) {
		this.billingType = billingType;
		return this;
	}

	public String getContractType() {
		return contractType;
	}

	public Subscription setContractType(String contractType) {
		this.contractType = contractType;
		return this;
	}

	public boolean isAutoRenewEnabled() {
		return autoRenewEnabled;
	}

	public Subscription setAutoRenewEnabled(boolean autoRenewEnabled) {
		this.autoRenewEnabled = autoRenewEnabled;
		return this;
	}

	public String getOrderId() {
		return orderId;
	}

	public Subscription setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Subscription setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
