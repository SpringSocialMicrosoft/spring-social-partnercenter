package org.springframework.social.partnercenter.api.utilities;

import org.springframework.http.ResponseEntity;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	ResponseEntity deleteCustomer(String customerId);
}
