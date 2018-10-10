package org.springframework.social.partnercenter.api.agreement;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AgreementMetaDataTemplate extends PagingResourceTemplate<AgreementMetaData> implements AgreementMetaDataOperations {

	private RestResource restResource;

	public AgreementMetaDataTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<AgreementMetaData>>() {});
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AgreementMetaData>> getAgreementMetadatas() {
		return restResource.request()
				.pathSegment("agreements")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AgreementMetaData>>() {});
	}
}
