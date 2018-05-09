package org.springframework.social.partnercenter.api.billing.invoicing.impl;

import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.invoicing.Invoice;
import org.springframework.social.partnercenter.api.billing.invoicing.InvoiceOperations;
import org.springframework.social.partnercenter.http.client.RestResource;

public class InvoiceTemplate extends AbstractTemplate implements InvoiceOperations{
	private RestResource restResource;

	public InvoiceTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<Invoice>> getInvoices() {
		return restResource.request()
				.get(new ParameterizedTypeReference<PartnerCenterResponse<Invoice>>() {});
	}

	@Override
	public ResponseEntity<Invoice> getById(String id) {
		notNull(id, "id");
		return restResource.request()
				.pathSegment(id)
				.get(Invoice.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
