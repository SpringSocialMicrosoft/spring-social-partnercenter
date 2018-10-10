package org.springframework.social.partnercenter.api.agreement;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceOperations;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;

public interface AgreementMetaDataOperations extends PagingResourceOperations<AgreementMetaData> {
	ResponseEntity<PartnerCenterResponse<AgreementMetaData>> getAgreementMetadatas();
}
