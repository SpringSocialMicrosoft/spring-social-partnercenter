package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UpgradeType {
	NONE("none"),
	UPGRADE_ONLY("upgrade_only"),
	UPGRADE_WITH_LICENSE_TRANSFER("upgrade_with_license_transfer");

	private String jsonValue;

	UpgradeType(String json){
		this.jsonValue = json;
	}

	@JsonValue
	public String jsonValue() {
		return this.jsonValue;
	}
}
