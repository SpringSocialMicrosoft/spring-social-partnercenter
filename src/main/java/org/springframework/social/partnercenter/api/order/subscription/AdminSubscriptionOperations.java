package org.springframework.social.partnercenter.api.order.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AdminSubscriptionOperations extends SubscriptionOperations{
	ResponseEntity<PartnerCenterResponse<Conversion>> conversions(String customerTenantId, String subscriptionId);
	ResponseEntity<ConversionResult> convertTrial(String customerTenantyId, String subscriptionId, Conversion conversion);
}
