package org.springframework.social.partnercenter.api.agreement;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.Instant;

import org.springframework.social.partnercenter.api.profile.Contact;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class Agreement {

	private String userId;
	private Contact primaryContact;
	private Instant dateAgreed;
	private String templateId;
	private AgreementType type;
	private String agreementLink;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Contact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Contact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Instant getDateAgreed() {
		return dateAgreed;
	}

	public void setDateAgreed(Instant dateAgreed) {
		this.dateAgreed = dateAgreed;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public AgreementType getType() {
		return type;
	}

	public void setType(AgreementType type) {
		this.type = type;
	}

	public String getAgreementLink() {
		return agreementLink;
	}

	public void setAgreementLink(String agreementLink) {
		this.agreementLink = agreementLink;
	}
}
