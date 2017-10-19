package org.springframework.social.partnercenter.api.order.subscription;

import java.time.ZonedDateTime;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.order.BillingCycle;
import org.springframework.social.partnercenter.api.order.BillingType;
import org.springframework.social.partnercenter.api.order.ContractType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {
	private String id;
	private String offerId;
	private String offerName;
	private String partnerId;
	private String friendlyName;
	private int quantity;
	private String unitType;
	private boolean hasPurchasableAddons;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private ZonedDateTime creationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
	private ZonedDateTime effectiveStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
	private ZonedDateTime commitmentEndDate;
	private SubscriptionStatus status;
	private boolean autoRenewEnabled;
	@JsonProperty("isTrial")
	private boolean trial;
	private BillingType billingType;
	private BillingCycle billingCycle;
	private ContractType contractType;
	private SubscriptionLinks links;
	private String orderId;
	private ResourceAttributes attributes;

	public String getId() {
		return id;
	}

	public Subscription setId(String id) {
		this.id = id;
		return this;
	}

	public String getOfferId() {
		return offerId;
	}

	public Subscription setOfferId(String offerId) {
		this.offerId = offerId;
		return this;
	}

	public String getOfferName() {
		return offerName;
	}

	public Subscription setOfferName(String offerName) {
		this.offerName = offerName;
		return this;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public Subscription setPartnerId(String partnerId) {
		this.partnerId = partnerId;
		return this;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public Subscription setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public Subscription setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getUnitType() {
		return unitType;
	}

	public Subscription setUnitType(String unitType) {
		this.unitType = unitType;
		return this;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public Subscription setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public ZonedDateTime getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public Subscription setEffectiveStartDate(ZonedDateTime effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
		return this;
	}

	public ZonedDateTime getCommitmentEndDate() {
		return commitmentEndDate;
	}

	public Subscription setCommitmentEndDate(ZonedDateTime commitmentEndDate) {
		this.commitmentEndDate = commitmentEndDate;
		return this;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public Subscription setStatus(SubscriptionStatus status) {
		this.status = status;
		return this;
	}

	public BillingType getBillingType() {
		return billingType;
	}

	public Subscription setBillingType(BillingType billingType) {
		this.billingType = billingType;
		return this;
	}

	public BillingCycle getBillingCycle() {
		return billingCycle;
	}

	public Subscription setBillingCycle(BillingCycle billingCycle) {
		this.billingCycle = billingCycle;
		return this;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public Subscription setContractType(ContractType contractType) {
		this.contractType = contractType;
		return this;
	}

	public boolean isAutoRenewEnabled() {
		return autoRenewEnabled;
	}

	public Subscription setAutoRenewEnabled(boolean autoRenewEnabled) {
		this.autoRenewEnabled = autoRenewEnabled;
		return this;
	}

	public String getOrderId() {
		return orderId;
	}

	public Subscription setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public SubscriptionLinks getLinks() {
		return links;
	}

	public Subscription setLinks(SubscriptionLinks links) {
		this.links = links;
		return this;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public Subscription setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
		return this;
	}

	public static SubscriptionBuilder builder(){
		return new SubscriptionBuilder();
	}

	public boolean isTrial() {
		return trial;
	}

	public void setIsTrial(boolean trial) {
		this.trial = trial;
	}

	public boolean isHasPurchasableAddons() {
		return hasPurchasableAddons;
	}

	public void setHasPurchasableAddons(boolean hasPurchasableAddons) {
		this.hasPurchasableAddons = hasPurchasableAddons;
	}

	public static class SubscriptionBuilder{
		private String id;
		private String offerId;
		private String offerName;
		private String partnerId;
		private String friendlyName;
		private int quantity;
		private String unitType;
		private boolean hasPurchasableAddons;
		private boolean isTrial;
		private ZonedDateTime creationDate;
		private ZonedDateTime effectiveStartDate;
		private ZonedDateTime commitmentEndDate;
		private SubscriptionStatus status;
		private BillingType billingType;
		private BillingCycle billingCycle;
		private ContractType contractType;
		private boolean autoRenewEnabled;
		private String orderId;
		private SubscriptionLinks links;
		private ResourceAttributes attributes;

		public SubscriptionBuilder id(String id) {
			this.id = id;
			return this;
		}

		public SubscriptionBuilder offerId(String offerId) {
			this.offerId = offerId;
			return this;
		}

		public SubscriptionBuilder offerName(String offerName) {
			this.offerName = offerName;
			return this;
		}

		public SubscriptionBuilder partnerId(String partnerId) {
			this.partnerId = partnerId;
			return this;
		}

		public SubscriptionBuilder friendlyName(String friendlyName) {
			this.friendlyName = friendlyName;
			return this;
		}

		public SubscriptionBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public SubscriptionBuilder unitType(String unitType) {
			this.unitType = unitType;
			return this;
		}

		public SubscriptionBuilder creationDate(ZonedDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public SubscriptionBuilder effectiveStartDate(ZonedDateTime effectiveStartDate) {
			this.effectiveStartDate = effectiveStartDate;
			return this;
		}

		public SubscriptionBuilder commitmentEndDate(ZonedDateTime commitmentEndDate) {
			this.commitmentEndDate = commitmentEndDate;
			return this;
		}

		public SubscriptionBuilder status(SubscriptionStatus status) {
			this.status = status;
			return this;
		}

		public SubscriptionBuilder billingType(BillingType billingType) {
			this.billingType = billingType;
			return this;
		}

		public SubscriptionBuilder billingCycle(BillingCycle billingCycle) {
			this.billingCycle = billingCycle;
			return this;
		}

		public SubscriptionBuilder contractType(ContractType contractType) {
			this.contractType = contractType;
			return this;
		}

		public SubscriptionBuilder autoRenewEnabled(boolean autoRenewEnabled) {
			this.autoRenewEnabled = autoRenewEnabled;
			return this;
		}

		public SubscriptionBuilder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public SubscriptionBuilder trial(boolean trial) {
			isTrial = trial;
			return this;
		}

		public SubscriptionBuilder links(SubscriptionLinks links) {
			this.links = links;
			return this;
		}

		public SubscriptionBuilder attributes(ResourceAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public SubscriptionBuilder hasPurchasableAddons(boolean hasPurchasableAddons) {
			this.hasPurchasableAddons = hasPurchasableAddons;
			return this;
		}

		public Subscription build(){
			Subscription subscription = new Subscription();
			subscription.setId(id);
			subscription.setContractType(contractType);
			subscription.setStatus(status);
			subscription.setLinks(links);
			subscription.setAttributes(attributes);
			subscription.setOfferId(offerId);
			subscription.setOrderId(orderId);
			subscription.setIsTrial(isTrial);
			subscription.setHasPurchasableAddons(hasPurchasableAddons);
			subscription.setAutoRenewEnabled(autoRenewEnabled);
			subscription.setBillingCycle(billingCycle);
			subscription.setBillingType(billingType);
			subscription.setContractType(contractType);
			subscription.setCommitmentEndDate(commitmentEndDate);
			subscription.setEffectiveStartDate(effectiveStartDate);
			subscription.setCreationDate(creationDate);
			subscription.setFriendlyName(friendlyName);
			subscription.setOfferName(offerName);
			subscription.setQuantity(quantity);
			subscription.setPartnerId(partnerId);
			subscription.setUnitType(unitType);

			return subscription;
		}
	}
}
