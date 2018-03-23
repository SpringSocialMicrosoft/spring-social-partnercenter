package org.springframework.social.partnercenter.api.billing.usage;

import java.math.BigDecimal;

public class SpendingBudget {
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
