package org.springframework.social.partnercenter.api.billing.usage;

import java.util.Map;

public class UtilizationRecord {
	private String usageStartTime;
	private String usageEndTime;
	private AzureResource resource;
	private double quantity;
	private String unit;
	private InfoFields infoFields;
	private Map<String, String> attributes;

	public String getUsageStartTime() {
		return usageStartTime;
	}

	public UtilizationRecord usageStartTime(String usageStartTime) {
		this.usageStartTime = usageStartTime;
		return this;
	}

	public String getUsageEndTime() {
		return usageEndTime;
	}

	public UtilizationRecord usageEndTime(String usageEndTime) {
		this.usageEndTime = usageEndTime;
		return this;
	}

	public AzureResource getResource() {
		return resource;
	}

	public UtilizationRecord resource(AzureResource resource) {
		this.resource = resource;
		return this;
	}

	public double getQuantity() {
		return quantity;
	}

	public UtilizationRecord quantity(double quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public UtilizationRecord unit(String unit) {
		this.unit = unit;
		return this;
	}

	public InfoFields getInfoFields() {
		return infoFields;
	}

	public UtilizationRecord infoFields(InfoFields infoFields) {
		this.infoFields = infoFields;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public UtilizationRecord attributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
