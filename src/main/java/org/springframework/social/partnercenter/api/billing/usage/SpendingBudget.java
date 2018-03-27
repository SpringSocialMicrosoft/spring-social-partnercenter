package org.springframework.social.partnercenter.api.billing.usage;

import java.math.BigDecimal;

import org.springframework.social.partnercenter.api.ResourceBase;

public class SpendingBudget extends ResourceBase{
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public SpendingBudget setAmount(BigDecimal amount) {
		this.amount = amount;
		return this;
	}
}
