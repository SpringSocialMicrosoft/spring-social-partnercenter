package org.springframework.social.partnercenter.api.billing.usage;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class PartnerUsageSummary extends UsageSummaryBase {
	private Integer customerOverBudget;
	private Integer customersTrendingOver;
	private Integer customersWithUsageBasedSubscriptions;
	private Collection<String> emailsToNotify;

	public Integer getCustomerOverBudget() {
		return customerOverBudget;
	}

	public void setCustomerOverBudget(Integer customerOverBudget) {
		this.customerOverBudget = customerOverBudget;
	}

	public Integer getCustomersTrendingOver() {
		return customersTrendingOver;
	}

	public void setCustomersTrendingOver(Integer customersTrendingOver) {
		this.customersTrendingOver = customersTrendingOver;
	}

	public Integer getCustomersWithUsageBasedSubscriptions() {
		return customersWithUsageBasedSubscriptions;
	}

	public void setCustomersWithUsageBasedSubscriptions(Integer customersWithUsageBasedSubscriptions) {
		this.customersWithUsageBasedSubscriptions = customersWithUsageBasedSubscriptions;
	}

	public Collection<String> getEmailsToNotify() {
		return emailsToNotify;
	}

	public void setEmailsToNotify(Collection<String> emailsToNotify) {
		this.emailsToNotify = emailsToNotify;
	}
}
