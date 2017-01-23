package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import java.util.List;
import java.util.Map;

import org.springframework.social.partnercenter.api.order.offer.Offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Upgrade {
	private Offer targetOffer;
	private UpgradeType upgradeType;
	private boolean isEligible;
	private int quantity;
	private List<UpgradeError> upgradeErrors;
	private Map<String, String> attributes;

	@JsonIgnore
	public static UpgradeBuilder builder(){
		return new UpgradeBuilder();
	}

	public Offer getTargetOffer() {
		return targetOffer;
	}

	public Upgrade setTargetOffer(Offer targetOffer) {
		this.targetOffer = targetOffer;
		return this;
	}

	public UpgradeType getUpgradeType() {
		return upgradeType;
	}

	public Upgrade setUpgradeType(UpgradeType upgradeType) {
		this.upgradeType = upgradeType;
		return this;
	}

	public boolean isEligible() {
		return isEligible;
	}

	public Upgrade setEligible(boolean eligible) {
		isEligible = eligible;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public Upgrade setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public List<UpgradeError> getUpgradeErrors() {
		return upgradeErrors;
	}

	public Upgrade setUpgradeErrors(List<UpgradeError> upgradeErrors) {
		this.upgradeErrors = upgradeErrors;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Upgrade setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public static class UpgradeBuilder {
		Upgrade upgrade;

		public UpgradeBuilder(){
			upgrade = new Upgrade();
		}

		public UpgradeBuilder setTargetOffer(Offer targetOffer) {
			upgrade.setTargetOffer(targetOffer);
			return this;
		}

		public UpgradeBuilder setUpgradeType(UpgradeType upgradeType) {
			upgrade.setUpgradeType(upgradeType);
			return this;
		}

		public UpgradeBuilder setEligible(boolean eligible) {
			upgrade.setEligible(eligible);
			return this;
		}

		public UpgradeBuilder setQuantity(int quantity) {
			upgrade.setQuantity(quantity);
			return this;
		}

//		public UpgradeBuilder setUpgradeErrors(List<String> upgradeErrors) {
//			upgrade.setUpgradeErrors(upgradeErrors);
//			return this;
//		}

		public UpgradeBuilder setAttributes(Map<String, String> attributes) {
			upgrade.setAttributes(attributes);
			return this;
		}

		public Upgrade build(){
			return upgrade;
		}
	}
}
