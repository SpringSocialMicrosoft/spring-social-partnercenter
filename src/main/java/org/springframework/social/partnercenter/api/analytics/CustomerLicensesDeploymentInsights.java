package org.springframework.social.partnercenter.api.analytics;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceAttributes;

public class CustomerLicensesDeploymentInsights {
	private Double licensesDeployed;
	private Double licensesSold;
	private Double deploymentPercent;
	private String customerId;
	private String customerName;
	private String productName;
	private String serviceCode;
	private ZonedDateTime processedDateTime;
	private String serviceName;
	private String channel;
	private ResourceAttributes attributes;

	public Double getLicensesDeployed() {
		return licensesDeployed;
	}

	public void setLicensesDeployed(Double licensesDeployed) {
		this.licensesDeployed = licensesDeployed;
	}

	public Double getLicensesSold() {
		return licensesSold;
	}

	public void setLicensesSold(Double licensesSold) {
		this.licensesSold = licensesSold;
	}

	public Double getDeploymentPercent() {
		return deploymentPercent;
	}

	public void setDeploymentPercent(Double deploymentPercent) {
		this.deploymentPercent = deploymentPercent;
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
