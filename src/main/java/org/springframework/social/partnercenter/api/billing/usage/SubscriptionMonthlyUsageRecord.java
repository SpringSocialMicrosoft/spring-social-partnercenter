package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.order.subscription.SubscriptionStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class SubscriptionMonthlyUsageRecord extends UsageRecordBase {
	private String offerId;
	private String partnerOnRecord;
	private SubscriptionStatus status;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getPartnerOnRecord() {
		return partnerOnRecord;
	}

	public void setPartnerOnRecord(String partnerOnRecord) {
		this.partnerOnRecord = partnerOnRecord;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}
}
