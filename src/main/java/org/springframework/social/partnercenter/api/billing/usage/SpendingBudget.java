package org.springframework.social.partnercenter.api.billing.usage;

import java.math.BigDecimal;

import org.springframework.social.partnercenter.api.ResourceBase;

public class SpendingBudget extends ResourceBase{
	private BigDecimal ammount;

	public BigDecimal getAmmount() {
		return ammount;
	}

	public SpendingBudget setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
		return this;
	}
}
