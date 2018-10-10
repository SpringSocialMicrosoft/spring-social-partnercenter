package org.springframework.social.partnercenter.api.agreement;

import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.http.client.RestResource;

public class AgreementTemplate extends PagingResourceTemplate<AgreementMetaData> implements AgreementOperations {
	private RestResource restResource;

	public AgreementTemplate(RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<AgreementMetaData>>() {});
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AgreementMetaData>> getAgreementMetadatas() {
		return restResource.request()
				.pathSegment("agreements")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AgreementMetaData>>() {});
	}

	@Override
	public ResponseEntity<Agreement> confirmCustomerAcceptance(String customerTenantId, Agreement agreement) {
		notNull(customerTenantId, "customerTenantId");
		notNull(agreement, "agreement");
		return restResource.request()
				.pathSegment("customers", customerTenantId, "agreements")
				.post(agreement, Agreement.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Agreement>> getConfirmations(String customerTenantId) {
		notNull(customerTenantId, "customerTenantId");
		return restResource.request()
				.pathSegment("customers", customerTenantId, "agreements")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Agreement>>() {});
	}
}
