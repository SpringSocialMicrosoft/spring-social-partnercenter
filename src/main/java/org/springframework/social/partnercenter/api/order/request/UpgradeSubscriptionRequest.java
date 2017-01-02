package org.springframework.social.partnercenter.api.order.request;

import java.util.List;
import java.util.Map;

import org.springframework.social.partnercenter.api.order.Subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpgradeSubscriptionRequest {
	private Subscription targetOffer;
	private int upgradeType;
	private boolean isEligible;
	private int quantity;
	private List<String> upgradeErrors;
	private Map<String, String> attributes;

	@JsonIgnore
	public static Builder builder(){
		return new Builder();
	}
	public Subscription getTargetOffer() {
		return targetOffer;
	}

	public UpgradeSubscriptionRequest setTargetOffer(Subscription targetOffer) {
		this.targetOffer = targetOffer;
		return this;
	}

	public int getUpgradeType() {
		return upgradeType;
	}

	public UpgradeSubscriptionRequest setUpgradeType(int upgradeType) {
		this.upgradeType = upgradeType;
		return this;
	}

	public boolean isEligible() {
		return isEligible;
	}

	public UpgradeSubscriptionRequest setEligible(boolean eligible) {
		isEligible = eligible;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public UpgradeSubscriptionRequest setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public List<String> getUpgradeErrors() {
		return upgradeErrors;
	}

	public UpgradeSubscriptionRequest setUpgradeErrors(List<String> upgradeErrors) {
		this.upgradeErrors = upgradeErrors;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public UpgradeSubscriptionRequest setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public static class Builder{
		UpgradeSubscriptionRequest upgradeSubscriptionRequest;

		public Builder(){
			upgradeSubscriptionRequest = new UpgradeSubscriptionRequest();
		}

		public Builder setTargetOffer(Subscription targetOffer) {
			upgradeSubscriptionRequest.setTargetOffer(targetOffer);
			return this;
		}

		public Builder setUpgradeType(int upgradeType) {
			upgradeSubscriptionRequest.setUpgradeType(upgradeType);
			return this;
		}

		public Builder setEligible(boolean eligible) {
			upgradeSubscriptionRequest.setEligible(eligible);
			return this;
		}

		public Builder setQuantity(int quantity) {
			upgradeSubscriptionRequest.setQuantity(quantity);
			return this;
		}

		public Builder setUpgradeErrors(List<String> upgradeErrors) {
			upgradeSubscriptionRequest.setUpgradeErrors(upgradeErrors);
			return this;
		}

		public Builder setAttributes(Map<String, String> attributes) {
			upgradeSubscriptionRequest.setAttributes(attributes);
			return this;
		}

		public UpgradeSubscriptionRequest build(){
			return upgradeSubscriptionRequest;
		}
	}
}
