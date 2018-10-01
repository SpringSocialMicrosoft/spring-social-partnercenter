package org.springframework.social.partnercenter.api.agreement;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AgreementOperations extends PagingResourceOperations<AgreementMetaData> {
	ResponseEntity<PartnerCenterResponse<AgreementMetaData>> getAgreementMetadatas();
	ResponseEntity<Agreement> confirmCustomerAcceptance(String customerTenantId, Agreement agreement);
	ResponseEntity<PartnerCenterResponse<Agreement>> getConfirmations(String customerTenantId);
}
