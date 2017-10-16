package org.springframework.social.partnercenter.api.utilities;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.audit.AuditRecord;
import org.springframework.social.partnercenter.api.customer.Address;
import org.springframework.social.partnercenter.api.customer.query.Filter;

public interface UtilityOperations {
	ResponseEntity<CountryInformation> getAddressFormattingRulesByMarket(String isoCodeId);
	Boolean isDomainAvailable(String domainId);
	ResponseEntity<Boolean> validateAddress(Address address);
	ResponseEntity deleteCustomer(String customerId);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate, Filter filter);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate, String filter);
	ResponseEntity<PartnerCenterResponse<AuditRecord>> getActivityByUser(Instant startDate, Instant endDate);
}
