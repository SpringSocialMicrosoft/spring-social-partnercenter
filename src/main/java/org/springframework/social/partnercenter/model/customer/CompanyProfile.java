package org.springframework.social.partnercenter.model.customer;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfile {

	@JsonProperty("TenantId")
	private Object tenantId;
	@JsonProperty("Domain")
	private String domain;
	@JsonProperty("CompanyName")
	private Object companyName;
	@JsonProperty("Attributes")
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
