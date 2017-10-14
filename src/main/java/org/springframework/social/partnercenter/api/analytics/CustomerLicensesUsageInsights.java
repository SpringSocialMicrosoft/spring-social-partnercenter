package org.springframework.social.partnercenter.api.analytics;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceAttributes;

public class CustomerLicensesUsageInsights {
	private String workloadCode;
	private String workloadName;
	private Double usagePercent;
	private Integer licensesActive;
	private Integer licensesQualified;
	private String customerId;
	private String customerName;
	private String productName;
	private String serviceCode;
	private ZonedDateTime processedDateTime;
	private String serviceName;
	private String channel;
	private ResourceAttributes attributes;

	public String getWorkloadCode() {
		return workloadCode;
	}

	public void setWorkloadCode(String workloadCode) {
		this.workloadCode = workloadCode;
	}

	public String getWorkloadName() {
		return workloadName;
	}

	public void setWorkloadName(String workloadName) {
		this.workloadName = workloadName;
	}

	public Double getUsagePercent() {
		return usagePercent;
	}

	public void setUsagePercent(Double usagePercent) {
		this.usagePercent = usagePercent;
	}

	public Integer getLicensesActive() {
		return licensesActive;
	}

	public void setLicensesActive(Integer licensesActive) {
		this.licensesActive = licensesActive;
	}

	public Integer getLicensesQualified() {
		return licensesQualified;
	}

	public void setLicensesQualified(Integer licensesQualified) {
		this.licensesQualified = licensesQualified;
	}

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public ZonedDateTime getProcessedDateTime() {
		return processedDateTime;
	}

	public void setProcessedDateTime(ZonedDateTime processedDateTime) {
		this.processedDateTime = processedDateTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}
}
