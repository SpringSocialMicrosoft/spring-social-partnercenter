package org.springframework.social.partnercenter.api.utilities;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.customer.Address;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	ResponseEntity<Boolean> validateAddress(Address address);
	ResponseEntity deleteCustomer(String customerId);
}
