package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceBaseWithLinks;
import org.springframework.social.partnercenter.api.StandardResourceLinks;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public abstract class UsageSummaryBase extends ResourceBaseWithLinks<StandardResourceLinks>{
	private ZonedDateTime billingStartDate;
	private ZonedDateTime billingEndDate;
	private String currencyLocale;
	private ZonedDateTime lastModifiedDate;
	private String resourceId;
	private String resourceName;
	private BigDecimal totalCost;

	public ZonedDateTime getBillingStartDate() {
		return billingStartDate;
	}

	public void setBillingStartDate(ZonedDateTime billingStartDate) {
		this.billingStartDate = billingStartDate;
	}

	public ZonedDateTime getBillingEndDate() {
		return billingEndDate;
	}

	public void setBillingEndDate(ZonedDateTime billingEndDate) {
		this.billingEndDate = billingEndDate;
	}

	public String getCurrencyLocale() {
		return currencyLocale;
	}

	public void setCurrencyLocale(String currencyLocale) {
		this.currencyLocale = currencyLocale;
	}

	public ZonedDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
}
