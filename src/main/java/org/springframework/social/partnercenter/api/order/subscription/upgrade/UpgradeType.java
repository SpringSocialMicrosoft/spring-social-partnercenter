package org.springframework.social.partnercenter.api.order.subscription.upgrade;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UpgradeType {
	@JsonProperty("none")
	NONE,
	@JsonProperty("upgrade_only")
	UPGRADE_ONLY,
	@JsonProperty("upgrade_with_license_transfer")
	UPGRADE_WITH_LICENSE_TRANSFER
}
