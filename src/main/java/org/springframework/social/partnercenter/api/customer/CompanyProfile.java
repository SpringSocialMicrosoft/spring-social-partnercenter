package org.springframework.social.partnercenter.api.customer;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfile {

	private Object tenantId;
	private String domain;
	private Object companyName;
	private Map<String, String> attributes;

	public Object getTenantId() {
		return tenantId;
	}

	public void setTenantId(Object tenantId) {
		this.tenantId = tenantId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Object getCompanyName() {
		return companyName;
	}

	public void setCompanyName(Object companyName) {
		this.companyName = companyName;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
