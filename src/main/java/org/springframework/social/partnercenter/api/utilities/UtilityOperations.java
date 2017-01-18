package org.springframework.social.partnercenter.api.utilities;

import org.springframework.http.ResponseEntity;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	ResponseEntity<Boolean> isDomainAvailable(String domainId);
	ResponseEntity deleteCustomer(String customerId);
}
