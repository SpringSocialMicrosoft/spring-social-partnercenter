package org.springframework.social.partnercenter.api.order.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SubscriptionStatus {
	@JsonProperty("active")
	ACTIVE,
	@JsonProperty("deleted")
	DELETED,
	@JsonProperty("none")
	NONE,
	@JsonProperty("suspended")
	SUSPENDED
}
