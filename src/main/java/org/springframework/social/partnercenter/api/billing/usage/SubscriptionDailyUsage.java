package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class SubscriptionDailyUsage extends UsageRecordBase {
	private OffsetDateTime  dateUsed;

	public OffsetDateTime getDateUsed() {
		return dateUsed;
	}

	public void setDateUsed(OffsetDateTime dateUsed) {
		this.dateUsed = dateUsed;
	}
}
