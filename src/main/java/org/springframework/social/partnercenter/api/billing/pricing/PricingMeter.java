package org.springframework.social.partnercenter.api.billing.pricing;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingMeter {
	private String id;
	private String name;
	private List<String> tags;
	private Map<String, Double> rates;
	private String category;
	private String subcategory;
	private String region;
	private String unit;
	private Double includedQuantity;
	private ZonedDateTime effectiveDate;

	public String getId() {
		return id;
	}

	public PricingMeter setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PricingMeter setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public PricingMeter setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public PricingMeter setRates(Map<String, Double> rates) {
		this.rates = rates;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public PricingMeter setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public PricingMeter setSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	public String getRegion() {
		return region;
	}

	public PricingMeter setRegion(String region) {
		this.region = region;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public PricingMeter setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public Double getIncludedQuantity() {
		return includedQuantity;
	}

	public PricingMeter setIncludedQuantity(Double includedQuantity) {
		this.includedQuantity = includedQuantity;
		return this;
	}

	public ZonedDateTime getEffectiveDate() {
		return effectiveDate;
	}

	public PricingMeter setEffectiveDate(ZonedDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
		return this;
	}
}
