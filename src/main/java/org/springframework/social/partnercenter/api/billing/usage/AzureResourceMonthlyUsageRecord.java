package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class AzureResourceMonthlyUsageRecord extends UsageRecordBase {
	private String category;
	private String subcategory;
	private BigDecimal quantityUsed;
	private String unit;
	private String id;
	private String name;

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

	public BigDecimal getQuantityUsed() {
		return quantityUsed;
	}

	public AzureResourceMonthlyUsageRecord setQuantityUsed(BigDecimal quantityUsed) {
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
}
