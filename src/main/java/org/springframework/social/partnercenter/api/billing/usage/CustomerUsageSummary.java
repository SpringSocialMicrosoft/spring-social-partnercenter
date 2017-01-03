package org.springframework.social.partnercenter.api.billing.usage;

import java.util.Map;

import org.springframework.social.partnercenter.api.Link;

public class CustomerUsageSummary {
	private Budget budget;
	private String id;
	private String name;
	private String billingStartDate;
	private String billingEndDate;
	private double totalCost;
	private String currencyLocale;
	private String lastModifiedDate;
	private Map<String, Link> links;
	private Map<String, String> attributes;

	public Budget getBudget() {
		return budget;
	}

	public CustomerUsageSummary setBudget(Budget budget) {
		this.budget = budget;
		return this;
	}

	public String getId() {
		return id;
	}

	public CustomerUsageSummary setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CustomerUsageSummary setName(String name) {
		this.name = name;
		return this;
	}

	public String getBillingStartDate() {
		return billingStartDate;
	}

	public CustomerUsageSummary setBillingStartDate(String billingStartDate) {
		this.billingStartDate = billingStartDate;
		return this;
	}

	public String getBillingEndDate() {
		return billingEndDate;
	}

	public CustomerUsageSummary setBillingEndDate(String billingEndDate) {
		this.billingEndDate = billingEndDate;
		return this;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public CustomerUsageSummary setTotalCost(double totalCost) {
		this.totalCost = totalCost;
		return this;
	}

	public String getCurrencyLocale() {
		return currencyLocale;
	}

	public CustomerUsageSummary setCurrencyLocale(String currencyLocale) {
		this.currencyLocale = currencyLocale;
		return this;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public CustomerUsageSummary setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public CustomerUsageSummary setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public CustomerUsageSummary setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
