package org.springframework.social.partnercenter.api.analytics;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceAttributes;

public class PartnerLicensesDeploymentInsights {
	private Double proratedDeploymentPercent;
	private Double licensesSold;
	private ZonedDateTime processedDateTime;
	private String serviceName;
	private String channel;
	private ResourceAttributes attributes;

	public Double getProratedDeploymentPercent() {
		return proratedDeploymentPercent;
	}

	public void setProratedDeploymentPercent(Double proratedDeploymentPercent) {
		this.proratedDeploymentPercent = proratedDeploymentPercent;
	}

	public Double getLicensesSold() {
		return licensesSold;
	}

	public void setLicensesSold(Double licensesSold) {
		this.licensesSold = licensesSold;
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
