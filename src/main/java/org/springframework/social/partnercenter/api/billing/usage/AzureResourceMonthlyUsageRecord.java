package org.springframework.social.partnercenter.api.billing.usage;

import java.util.Map;

public class AzureResourceMonthlyUsageRecord {
	private String category;
	private String subcategory;
	private double quantityUsed;
	private String unit;
	private String id;
	private String name;
	private double totalCost;
	private String currencyLocale;
	private Map<String, String> attributes;

	public String getCategory() {
		return category;
	}

	public AzureResourceMonthlyUsageRecord setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public AzureResourceMonthlyUsageRecord setSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public double getQuantityUsed() {
		return quantityUsed;
	}

	public AzureResourceMonthlyUsageRecord setQuantityUsed(double quantityUsed) {
		this.quantityUsed = quantityUsed;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public AzureResourceMonthlyUsageRecord setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public String getId() {
		return id;
	}

	public AzureResourceMonthlyUsageRecord setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public AzureResourceMonthlyUsageRecord setName(String name) {
		this.name = name;
		return this;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public AzureResourceMonthlyUsageRecord setTotalCost(double totalCost) {
		this.totalCost = totalCost;
		return this;
	}

	public String getCurrencyLocale() {
		return currencyLocale;
	}

	public AzureResourceMonthlyUsageRecord setCurrencyLocale(String currencyLocale) {
		this.currencyLocale = currencyLocale;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public AzureResourceMonthlyUsageRecord setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
