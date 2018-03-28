package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class CustomerMonthlyUsageRecord extends UsageRecordBase {
	private SpendingBudget budget;
	private BigDecimal percentUsed;

	public SpendingBudget getBudget() {
		return budget;
	}

	public void setBudget(SpendingBudget budget) {
		this.budget = budget;
	}

	public BigDecimal getPercentUsed() {
		return percentUsed;
	}

	public void setPercentUsed(BigDecimal percentUsed) {
		this.percentUsed = percentUsed;
	}
}
