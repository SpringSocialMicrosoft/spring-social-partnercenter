package org.springframework.social.partnercenter.api.utilities;

public interface UtilityOperations {
	CountryInformation getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	void deleteCustomer(String customerId);
}
