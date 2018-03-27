package org.springframework.social.partnercenter.api.billing.usage;

public final class CustomerUsageSummary extends UsageSummaryBase {
	private SpendingBudget budget;

	public SpendingBudget getBudget() {
		return budget;
	}

	public CustomerUsageSummary setBudget(SpendingBudget budget) {
		this.budget = budget;
		return this;
	}
}
