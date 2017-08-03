package org.springframework.social.partnercenter.api.order.subscription;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ConversionError {
	private String subscriptionId;
	private String offerId;
	private String targetOfferId;
	private ConversionError error;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTargetOfferId() {
		return targetOfferId;
	}

	public void setTargetOfferId(String targetOfferId) {
		this.targetOfferId = targetOfferId;
	}

	public ConversionError getError() {
		return error;
	}

	public void setError(ConversionError error) {
		this.error = error;
	}
}
