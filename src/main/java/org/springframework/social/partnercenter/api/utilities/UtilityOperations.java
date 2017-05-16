package org.springframework.social.partnercenter.api.utilities;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.customer.request.Filter;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	ResponseEntity deleteCustomer(String customerId);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate, Filter filter);
}
