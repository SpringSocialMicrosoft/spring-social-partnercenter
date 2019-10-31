package org.springframework.social.partnercenter.api.order.offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.order.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("minimumQuantity")
	private BigDecimal minimumQuantity;
	@JsonProperty("maximumQuantity")
	private BigDecimal maximumQuantity;
	@JsonProperty("rank")
	private int rank;
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("locale")
	private String locale;
	@JsonProperty("country")
	private String country;
	@JsonProperty("category")
	private OfferCategory category;
	@JsonProperty("prerequisiteOffers")
	private List<String> prerequisiteOffers;
	@JsonProperty("upgradeTargetOffers")
	private List<String> upgradeTargetOffers;
	@JsonProperty("conversionTargetOffers")
	private List<String> conversionTargetOffers;
	@JsonProperty("reselleeQualifications")
	private List<String> reselleeQualifications;
	@JsonProperty("resellerQualifications")
	private List<String> resellerQualifications;
	@JsonProperty("salesGroupId")
	private String salesGroupId;
	@JsonProperty("isAddOn")
	private boolean isAddOn;
	@JsonProperty("hasAddOns")
	private boolean hasAddOns;
	@JsonProperty("isAvailableForPurchase")
	private boolean isAvailableForPurchase;
	@JsonProperty("billing")
	private String billing;
	@JsonProperty("isAutoRenewable")
	private boolean isAutoRenewable;
	@JsonProperty("product")
	private Product product;
	@JsonProperty("unitType")
	private String unitType;
	@JsonProperty("isTrial")
	private boolean trial;
	@JsonProperty("supportedBillingCycles")
	private List<String> supportedBillingCycles;
	@JsonProperty("isInternal")
	private boolean internal;
	@JsonProperty("links")
	private Map<String, Link> links;
	@JsonProperty("attributes")
	private Map<String, String> attributes;
}
