package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceBase;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class UsageRecordBase extends ResourceBase{
	private String currencyLocale;
	private BigDecimal totalCost;
	private String resourceName;
	private String resourceId;
	private ZonedDateTime lastModifiedDate;

	public String getCurrencyLocale() {
		return currencyLocale;
	}

	public void setCurrencyLocale(String currencyLocale) {
		this.currencyLocale = currencyLocale;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public ZonedDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
