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

	public void setUsageStartTime(String usageStartTime) {
		this.usageStartTime = usageStartTime;
	}

	public String getUsageEndTime() {
		return usageEndTime;
	}

	public void setUsageEndTime(String usageEndTime) {
		this.usageEndTime = usageEndTime;
	}

	public AzureResource getResource() {
		return resource;
	}

	public void setResource(AzureResource resource) {
		this.resource = resource;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public InfoFields getInfoFields() {
		return infoFields;
	}

	public void setInfoFields(InfoFields infoFields) {
		this.infoFields = infoFields;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
