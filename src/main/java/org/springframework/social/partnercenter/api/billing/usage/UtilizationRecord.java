package org.springframework.social.partnercenter.api.billing.usage;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceBase;

public class UtilizationRecord extends ResourceBase {
	private ZonedDateTime usageStartTime;
	private ZonedDateTime usageEndTime;
	private AzureResource resource;
	private BigDecimal quantity;
	private String unit;
	private InfoFields infoFields;

	public ZonedDateTime getUsageStartTime() {
		return usageStartTime;
	}

	public void setUsageStartTime(ZonedDateTime usageStartTime) {
		this.usageStartTime = usageStartTime;
	}

	public ZonedDateTime getUsageEndTime() {
		return usageEndTime;
	}

	public void setUsageEndTime(ZonedDateTime usageEndTime) {
		this.usageEndTime = usageEndTime;
	}

	public AzureResource getResource() {
		return resource;
	}

	public void setResource(AzureResource resource) {
		this.resource = resource;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
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
}
