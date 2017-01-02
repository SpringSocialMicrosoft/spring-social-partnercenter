package org.springframework.social.partnercenter.api.billing.pricing;

import java.util.List;
import java.util.Map;

public class PricingMeters {
	private String id;
	private String name;
	private List<String> tags;
	private Map<String, Double> rates;
	private String category;
	private String subcategory;
	private String region;
	private String unit;
	private double includedQuantity;
	private String effectiveDate;

	public String getId() {
		return id;
	}

	public PricingMeters setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PricingMeters setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public PricingMeters setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public PricingMeters setRates(Map<String, Double> rates) {
		this.rates = rates;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public PricingMeters setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public PricingMeters setSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public String getRegion() {
		return region;
	}

	public PricingMeters setRegion(String region) {
		this.region = region;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public PricingMeters setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public double getIncludedQuantity() {
		return includedQuantity;
	}

	public PricingMeters setIncludedQuantity(double includedQuantity) {
		this.includedQuantity = includedQuantity;
		return this;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public PricingMeters setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
		return this;
	}
}
