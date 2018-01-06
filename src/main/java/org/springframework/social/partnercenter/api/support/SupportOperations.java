package org.springframework.social.partnercenter.api.support;

import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.support.managedservice.ManagedService;
import org.springframework.social.partnercenter.api.support.servicerequest.ServiceRequest;

public interface SupportOperations {
	ResponseEntity<SupportContact> getSupportContact(String customerId, String subscriptionId);
	ResponseEntity<SupportContact> updateSupportContact(String customerId, String subscriptionId, SupportContact supportContact);
	ResponseEntity<PartnerCenterResponse<ManagedService>> getManagedServices(String customerId);
	ResponseEntity<PartnerCenterResponse<SupportTopic>> getSupportTopics();
	ResponseEntity<PartnerCenterResponse<ServiceRequest>> getServiceRequests(String customerId);
	ResponseEntity<ServiceRequest> updateServiceRequest(ServiceRequest request);
	ResponseEntity<ServiceRequest> createServiceRequest(ServiceRequest request);
}
