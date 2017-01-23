package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import java.util.List;
import java.util.Map;

public class UpgradeResult {
	private String sourceSubscriptionId;
	private String targetSubscriptionId;
	private int upgradeType;
	private List<UpgradeError> upgradeErrors;
	private List<String> licenseErrors;
	private Map<String, String> attributes;

	public String getSourceSubscriptionId() {
		return sourceSubscriptionId;
	}

	public void setSourceSubscriptionId(String sourceSubscriptionId) {
		this.sourceSubscriptionId = sourceSubscriptionId;
	}

	public String getTargetSubscriptionId() {
		return targetSubscriptionId;
	}

	public void setTargetSubscriptionId(String targetSubscriptionId) {
		this.targetSubscriptionId = targetSubscriptionId;
	}

	public int getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(int upgradeType) {
		this.upgradeType = upgradeType;
	}

	public List<UpgradeError> getUpgradeErrors() {
		return upgradeErrors;
	}

	public void setUpgradeErrors(List<UpgradeError> upgradeErrors) {
		this.upgradeErrors = upgradeErrors;
	}

	public List<String> getLicenseErrors() {
		return licenseErrors;
	}

	public void setLicenseErrors(List<String> licenseErrors) {
		this.licenseErrors = licenseErrors;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
