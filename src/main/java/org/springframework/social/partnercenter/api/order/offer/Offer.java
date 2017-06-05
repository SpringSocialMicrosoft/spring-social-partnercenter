package org.springframework.social.partnercenter.api.order.offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.social.partnercenter.api.Link;
import org.springframework.social.partnercenter.api.order.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public String getId() {
		return id;
	}

	public Offer setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Offer setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Offer setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getMinimumQuantity() {
		return minimumQuantity;
	}

	public Offer setMinimumQuantity(BigDecimal minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
		return this;
	}

	public BigDecimal getMaximumQuantity() {
		return maximumQuantity;
	}

	public Offer setMaximumQuantity(BigDecimal maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
		return this;
	}

	public int getRank() {
		return rank;
	}

	public Offer setRank(int rank) {
		this.rank = rank;
		return this;
	}

	public String getUri() {
		return uri;
	}

	public Offer setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String getLocale() {
		return locale;
	}

	public Offer setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Offer setCountry(String country) {
		this.country = country;
		return this;
	}

	public OfferCategory getCategory() {
		return category;
	}

	public Offer setCategory(OfferCategory category) {
		this.category = category;
		return this;
	}

	public List<String> getPrerequisiteOffers() {
		return prerequisiteOffers;
	}

	public Offer setPrerequisiteOffers(List<String> prerequisiteOffers) {
		this.prerequisiteOffers = prerequisiteOffers;
		return this;
	}

	public boolean isAddOn() {
		return isAddOn;
	}

	public Offer setAddOn(boolean addOn) {
		isAddOn = addOn;
		return this;
	}

	public boolean isHasAddOns() {
		return hasAddOns;
	}

	public Offer setHasAddOns(boolean hasAddOns) {
		this.hasAddOns = hasAddOns;
		return this;
	}

	public boolean isAvailableForPurchase() {
		return isAvailableForPurchase;
	}

	public Offer setAvailableForPurchase(boolean availableForPurchase) {
		isAvailableForPurchase = availableForPurchase;
		return this;
	}

	public String getBilling() {
		return billing;
	}

	public Offer setBilling(String billing) {
		this.billing = billing;
		return this;
	}

	public boolean isAutoRenewable() {
		return isAutoRenewable;
	}

	public Offer setAutoRenewable(boolean autoRenewable) {
		isAutoRenewable = autoRenewable;
		return this;
	}

	public Product getProduct() {
		return product;
	}

	public Offer setProduct(Product product) {
		this.product = product;
		return this;
	}

	public String getUnitType() {
		return unitType;
	}

	public Offer setUnitType(String unitType) {
		this.unitType = unitType;
		return this;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public Offer setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Offer setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public List<String> getConversionTargetOffers() {
		return conversionTargetOffers;
	}

	public void setConversionTargetOffers(List<String> conversionTargetOffers) {
		this.conversionTargetOffers = conversionTargetOffers;
	}

	public List<String> getReselleeQualifications() {
		return reselleeQualifications;
	}

	public void setReselleeQualifications(List<String> reselleeQualifications) {
		this.reselleeQualifications = reselleeQualifications;
	}

	public List<String> getResellerQualifications() {
		return resellerQualifications;
	}

	public void setResellerQualifications(List<String> resellerQualifications) {
		this.resellerQualifications = resellerQualifications;
	}

	public String getSalesGroupId() {
		return salesGroupId;
	}

	public void setSalesGroupId(String salesGroupId) {
		this.salesGroupId = salesGroupId;
	}

	public boolean isTrial() {
		return trial;
	}

	public void setTrial(boolean trial) {
		this.trial = trial;
	}

	public List<String> getSupportedBillingCycles() {
		return supportedBillingCycles;
	}

	public void setSupportedBillingCycles(List<String> supportedBillingCycles) {
		this.supportedBillingCycles = supportedBillingCycles;
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}

	public static OfferBuilder builder(){
		return new OfferBuilder();
	}

	public static class OfferBuilder{
		private Offer offer = new Offer();
		private String id;
		private String name;
		private String description;
		private BigDecimal minimumQuantity;
		private BigDecimal maximumQuantity;
		private int rank;
		private String uri;
		private String locale;
		private String country;
		private OfferCategory category;
		private List<String> prerequisiteOffers;
		private boolean isAddOn;
		private boolean hasAddOns;
		private boolean isAvailableForPurchase;
		private String billing;
		private boolean isAutoRenewable;
		private Product product;
		private String unitType;
		private Map<String, Link> links;
		private Map<String, String> attributes;
		private List<String> conversionTargetOffers;
		private List<String> reselleeQualifications;
		private List<String> resellerQualifications;
		private List<String> supportedBillingCycles;
		private String salesGroupId;
		private boolean internal;
		private boolean trial;

		public Offer build(){
			Offer offer = new Offer();
			offer.setAttributes(attributes);
			offer.setId(id);
			offer.setName(name);
			offer.setDescription(description);
			offer.setMinimumQuantity(minimumQuantity);
			offer.setMaximumQuantity(maximumQuantity);
			offer.setRank(rank);
			offer.setUri(uri);
			offer.setLinks(links);
			offer.setLocale(locale);
			offer.setCountry(country);
			offer.setCategory(category);
			offer.setPrerequisiteOffers(prerequisiteOffers);
			offer.setAddOn(isAddOn);
			offer.setHasAddOns(hasAddOns);
			offer.setAvailableForPurchase(isAvailableForPurchase);
			offer.setBilling(billing);
			offer.setAutoRenewable(isAutoRenewable);
			offer.setProduct(product);
			offer.setUnitType(unitType);
			offer.setSupportedBillingCycles(supportedBillingCycles);
			offer.setConversionTargetOffers(conversionTargetOffers);
			offer.setReselleeQualifications(reselleeQualifications);
			offer.setResellerQualifications(resellerQualifications);
			offer.setSalesGroupId(salesGroupId);
			offer.setInternal(internal);
			offer.setTrial(trial);

			return offer;
		}
		public OfferBuilder id(String id) {
			this.id = id;
			return this;
		}

		public OfferBuilder name(String name) {
			this.name = name;
			return this;
		}

		public OfferBuilder description(String description) {
			this.description = description;
			return this;
		}

		public OfferBuilder minimumQuantity(BigDecimal minimumQuantity) {
			this.minimumQuantity = minimumQuantity;
			return this;
		}

		public OfferBuilder maximumQuantity(BigDecimal maximumQuantity) {
			this.maximumQuantity = maximumQuantity;
			return this;
		}

		public OfferBuilder rank(int rank) {
			this.rank = rank;
			return this;
		}

		public OfferBuilder uri(String uri) {
			this.uri = uri;
			return this;
		}

		public OfferBuilder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public OfferBuilder country(String country) {
			this.country = country;
			return this;
		}

		public OfferBuilder category(OfferCategory category) {
			this.category = category;
			return this;
		}

		public OfferBuilder prerequisiteOffers(List<String> prerequisiteOffers) {
			this.prerequisiteOffers = prerequisiteOffers;
			return this;
		}

		public OfferBuilder addOn(boolean addOn) {
			isAddOn = addOn;
			return this;
		}

		public OfferBuilder hasAddOns(boolean hasAddOns) {
			this.hasAddOns = hasAddOns;
			return this;
		}

		public OfferBuilder availableForPurchase(boolean availableForPurchase) {
			isAvailableForPurchase = availableForPurchase;
			return this;
		}

		public OfferBuilder billing(String billing) {
			this.billing = billing;
			return this;
		}

		public OfferBuilder autoRenewable(boolean autoRenewable) {
			isAutoRenewable = autoRenewable;
			return this;
		}

		public OfferBuilder product(Product product) {
			this.product = product;
			return this;
		}

		public OfferBuilder unitType(String unitType) {
			this.unitType = unitType;
			return this;
		}

		public OfferBuilder links(Map<String, Link> links) {
			this.links = links;
			return this;
		}

		public OfferBuilder attributes(Map<String, String> attributes) {
			this.attributes = attributes;
			return this;
		}

		public OfferBuilder conversionTargetOffers(List<String> conversionTargetOffers) {
			this.conversionTargetOffers = conversionTargetOffers;
			return this;
		}

		public OfferBuilder reselleeQualifications(List<String> reselleeQualifications) {
			this.reselleeQualifications = reselleeQualifications;
			return this;
		}

		public OfferBuilder resellerQualifications(List<String> resellerQualifications) {
			this.resellerQualifications = resellerQualifications;
			return this;
		}

		public OfferBuilder supportedBillingCycles(List<String> supportedBillingCycles) {
			this.supportedBillingCycles = supportedBillingCycles;
			return this;
		}

		public OfferBuilder salesGroupId(String salesGroupId) {
			this.salesGroupId = salesGroupId;
			return this;
		}

		public OfferBuilder internal(boolean internal) {
			this.internal = internal;
			return this;
		}

		public OfferBuilder trial(boolean trial) {
			this.trial = trial;
			return this;
		}
	}
}
