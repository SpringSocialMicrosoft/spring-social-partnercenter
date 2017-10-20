package org.springframework.social.partnercenter.api.analytics;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceAttributes;

public class PartnerLicensesUsageInsights {
	private Double proratedLicensesUsagePercent;
	private String workloadName;
	private ZonedDateTime processedDateTime;
	private String serviceName;
	private String channel;
	private ResourceAttributes attributes;

	public Double getProratedLicensesUsagePercent() {
		return proratedLicensesUsagePercent;
	}

	public void setProratedLicensesUsagePercent(Double proratedLicensesUsagePercent) {
		this.proratedLicensesUsagePercent = proratedLicensesUsagePercent;
	}

	public String getWorkloadName() {
		return workloadName;
	}

	public void setWorkloadName(String workloadName) {
		this.workloadName = workloadName;
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
