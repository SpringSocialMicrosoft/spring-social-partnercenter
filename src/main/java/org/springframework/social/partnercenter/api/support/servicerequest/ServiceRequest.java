package org.springframework.social.partnercenter.api.support.servicerequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ServiceRequest {
	private String id;
	private String title;
	private String description;
	private String supportTopicId;
	private String supportTopicName;
	private ServiceRequestSeverity severity;
	private ServiceRequestStatus status;
	private ServiceRequestOrganization organization;
	private ServiceRequestContact primaryContact;
	private ServiceRequestContact lastUpdatedBy;
	private String productName;
	private String productId;
	private List<FileInfo> fileLinks;
	private ServiceRequestNote newNote;
	private List<ServiceRequestNote> notes;
	private ZonedDateTime createdDate;
	private ZonedDateTime lastModifiedDate;
	private ZonedDateTime lastClosedDate;
	private String countryCode;
	private ResourceAttributes attributes;

	public ServiceRequest() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ServiceRequestSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(ServiceRequestSeverity severity) {
		this.severity = severity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ServiceRequestStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceRequestStatus status) {
		this.status = status;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ZonedDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public ZonedDateTime getLastClosedDate() {
		return lastClosedDate;
	}

	public void setLastClosedDate(ZonedDateTime lastClosedDate) {
		this.lastClosedDate = lastClosedDate;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}

	public ServiceRequestOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(ServiceRequestOrganization organization) {
		this.organization = organization;
	}

	public ServiceRequestContact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(ServiceRequestContact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public ServiceRequestContact getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(ServiceRequestContact lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ServiceRequestNote getNewNote() {
		return newNote;
	}

	public void setNewNote(ServiceRequestNote newNote) {
		this.newNote = newNote;
	}

	public List<ServiceRequestNote> getNotes() {
		return notes;
	}

	public void setNotes(List<ServiceRequestNote> notes) {
		this.notes = notes;
	}

	public List<FileInfo> getFileLinks() {
		return fileLinks;
	}

	public void setFileLinks(List<FileInfo> fileLinks) {
		this.fileLinks = fileLinks;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupportTopicId() {
		return supportTopicId;
	}

	public void setSupportTopicId(String supportTopicId) {
		this.supportTopicId = supportTopicId;
	}

	public String getSupportTopicName() {
		return supportTopicName;
	}

	public void setSupportTopicName(String supportTopicName) {
		this.supportTopicName = supportTopicName;
	}

	public static class Builder {
		private String title;
		private String description;
		private ServiceRequestSeverity severity;
		private ServiceRequestOrganization organization;
		private ServiceRequestContact primaryContact;
		private String supportTopicId;
		private String supportTopicName;
		private String productName;
		private String productId;
		private ServiceRequestNote newNote;
		private String countryCode;

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder severity(ServiceRequestSeverity severity) {
			this.severity = severity;
			return this;
		}

		public Builder organization(ServiceRequestOrganization organization) {
			this.organization = organization;
			return this;
		}

		public Builder primaryContact(ServiceRequestContact primaryContact) {
			this.primaryContact = primaryContact;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder productId(String productId) {
			this.productId = productId;
			return this;
		}

		public Builder newNote(ServiceRequestNote newNote) {
			this.newNote = newNote;
			return this;
		}

		public Builder countryCode(String countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public ServiceRequest build() {
			ServiceRequest request = new ServiceRequest();
			request.setTitle(title);
			request.setCountryCode(countryCode);
			request.setSeverity(severity);
			request.setOrganization(organization);
			request.setPrimaryContact(primaryContact);
			request.setProductName(productName);
			request.setProductId(productId);
			request.setNewNote(newNote);
			request.setSupportTopicId(supportTopicId);
			request.setSupportTopicName(supportTopicName);
			request.setDescription(description);
			return request;
		}

		public Builder supportTopicId(String supportTopicId) {
			this.supportTopicId = supportTopicId;
			return this;
		}

		public Builder supportTopicName(String supportTopicName) {
			this.supportTopicName = supportTopicName;
			return this;
		}
	}
}
