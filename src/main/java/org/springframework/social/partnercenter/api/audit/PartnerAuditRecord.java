package org.springframework.social.partnercenter.api.audit;

public class PartnerAuditRecord extends AuditRecord {
	private String partnerId;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
