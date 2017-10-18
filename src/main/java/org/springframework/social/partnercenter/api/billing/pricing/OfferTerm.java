package org.springframework.social.partnercenter.api.billing.pricing;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferTerm {
	@JsonProperty("name")
	private String name;
	@JsonProperty("discount")
	private Double discount;
	private List<String> excludedMeterIds;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
	private Instant effectiveDate;

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

	public Instant getEffectiveDate() {
		return effectiveDate;
	}

	public OfferTerm setEffectiveDate(Instant effectiveDate) {
		this.effectiveDate = effectiveDate;
		return this;
	}
}
