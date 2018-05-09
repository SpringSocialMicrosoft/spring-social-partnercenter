package org.springframework.social.partnercenter.api.support.impl;

import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;

import java.util.Locale;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.AbstractTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.support.SupportContact;
import org.springframework.social.partnercenter.api.support.SupportOperations;
import org.springframework.social.partnercenter.api.support.SupportTopic;
import org.springframework.social.partnercenter.api.support.managedservice.ManagedService;
import org.springframework.social.partnercenter.api.support.servicerequest.ServiceRequest;
import org.springframework.social.partnercenter.http.client.RestResource;

public class SupportTemplate extends AbstractTemplate implements SupportOperations {
	private static final String CUSTOMERS = "customers";
	private static final String SUBSCRIPTIONS = "subscriptions";
	private static final String SUPPORT_CONTACT = "supportcontact";
	private static final String MANAGED_SERVICES = "managedservices";
	private static final String SERVICE_REQUESTS = "servicerequests";

	private final RestResource restResource;

	public SupportTemplate(RestResource restResource, boolean isAuthorized) {
		super(isAuthorized);
		this.restResource = restResource;
	}

	@Override
	public ResponseEntity<SupportContact> getSupportContact(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(CUSTOMERS, customerId, SUBSCRIPTIONS, subscriptionId, SUPPORT_CONTACT)
				.get(SupportContact.class);
	}

	@Override
	public ResponseEntity<SupportContact> updateSupportContact(String customerId, String subscriptionId, SupportContact supportContact) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(supportContact, "supportContact");
		return restResource.request()
				.pathSegment(CUSTOMERS, customerId, SUBSCRIPTIONS, subscriptionId, SUPPORT_CONTACT)
				.put(supportContact, SupportContact.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<ManagedService>> getManagedServices(String customerId) {
		return restResource.request()
				.pathSegment(CUSTOMERS, customerId, MANAGED_SERVICES)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<ManagedService>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<SupportTopic>> getSupportTopics() {
		return restResource.request()
				.pathSegment(SERVICE_REQUESTS, "supporttopics")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<SupportTopic>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<ServiceRequest>> getServiceRequests(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(CUSTOMERS, customerId, SERVICE_REQUESTS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<ServiceRequest>>() {});
	}

	@Override
	public ResponseEntity<ServiceRequest> updateServiceRequest(ServiceRequest serviceRequest) {
		notNull(serviceRequest, "serviceRequest");
		return restResource.request()
				.pathSegment(SERVICE_REQUESTS, serviceRequest.getId())
				.patch(serviceRequest, ServiceRequest.class);
	}

	@Override
	public ResponseEntity<ServiceRequest> createServiceRequest(ServiceRequest serviceRequest, Locale locale) {
		notNull(serviceRequest, "serviceRequest");
		notNull(locale, "locale");
		return restResource.request()
				.pathSegment(SERVICE_REQUESTS, locale.toLanguageTag())
				.post(serviceRequest, ServiceRequest.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}
}
