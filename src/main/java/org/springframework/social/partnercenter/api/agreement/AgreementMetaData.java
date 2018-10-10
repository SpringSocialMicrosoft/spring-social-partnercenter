package org.springframework.social.partnercenter.api.agreement;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class AgreementMetaData {

	private String templateId;
	private AgreementType agreementType;
	private String agreementLink;
	private Integer versionRank;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public AgreementType getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(AgreementType agreementType) {
		this.agreementType = agreementType;
	}

	public String getAgreementLink() {
		return agreementLink;
	}

	public void setAgreementLink(String agreementLink) {
		this.agreementLink = agreementLink;
	}

	public Integer getVersionRank() {
		return versionRank;
	}

	public void setVersionRank(Integer versionRank) {
		this.versionRank = versionRank;
	}
}
