package org.springframework.social.partnercenter.api.billing.usage;

public final class CustomerUsageSummary extends UsageSummaryBase {
	private Budget budget;

	public Budget getBudget() {
		return budget;
	}

	public CustomerUsageSummary setBudget(Budget budget) {
		this.budget = budget;
		return this;
	}
}
