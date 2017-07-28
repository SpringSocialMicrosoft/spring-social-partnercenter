package org.springframework.social.partnercenter.api.billing.pricing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferTerm {
	@JsonProperty("name")
	private String name;
	@JsonProperty("discount")
	private Double discount;
	private List<String> excludedMeterIds;
	private String effectiveDate;

	public String getName() {
		return name;
	}

	public OfferTerm setName(String name) {
		this.name = name;
		return this;
	}

	public Double getDiscount() {
		return discount;
	}

	public OfferTerm setDiscount(Double discount) {
		this.discount = discount;
		return this;
	}

	public List<String> getExcludedMeterIds() {
		return excludedMeterIds;
	}

	public OfferTerm setExcludedMeterIds(List<String> excludedMeterIds) {
		this.excludedMeterIds = excludedMeterIds;
		return this;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public OfferTerm setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
		return this;
	}
}
