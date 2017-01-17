package org.springframework.social.partnercenter.api.customer.user.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AssignLicensesToUserRequest {
	LicensesToAssign licensesToAssign;
	List<String> licensesToRemove;
	List<String> licenseWarnings;
	Map<String, String> attributes;

	public LicensesToAssign getLicensesToAssign() {
		return licensesToAssign;
	}

	public void setLicensesToAssign(LicensesToAssign licensesToAssign) {
		this.licensesToAssign = licensesToAssign;
	}

	public List<String> getLicensesToRemove() {
		return licensesToRemove;
	}

	public void setLicensesToRemove(List<String> licensesToRemove) {
		this.licensesToRemove = licensesToRemove;
	}

	public List<String> getLicenseWarnings() {
		return licenseWarnings;
	}

	public void setLicenseWarnings(List<String> licenseWarnings) {
		this.licenseWarnings = licenseWarnings;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@JsonIgnore
	public static AssignLicensesToUserRequestBuilder builder(){
		return new AssignLicensesToUserRequestBuilder();
	}

	public static class AssignLicensesToUserRequestBuilder {
		LicensesToAssign licensesToAssign;
		List<String> licensesToRemove;
		List<String> licenseWarnings;
		Map<String, String> attributes;

		public AssignLicensesToUserRequestBuilder licensesToAssign(LicensesToAssign licensesToAssign) {
			this.licensesToAssign = licensesToAssign;
			return this;
		}

		public AssignLicensesToUserRequestBuilder licensesToRemove(List<String> licensesToRemove) {
			this.licensesToRemove = licensesToRemove;
			return this;
		}

		public AssignLicensesToUserRequestBuilder licenseWarnings(List<String> licenseWarnings) {
			this.licenseWarnings = licenseWarnings;
			return this;
		}

		public AssignLicensesToUserRequestBuilder attributes(Map<String, String> attributes) {
			this.attributes = attributes;
			return this;
		}

		public AssignLicensesToUserRequest build(){
			AssignLicensesToUserRequest request = new AssignLicensesToUserRequest();
			request.setAttributes(attributes);
			request.setLicensesToAssign(licensesToAssign);
			request.setLicensesToRemove(licensesToRemove);
			request.setLicenseWarnings(licenseWarnings);
			return request;
		}
	}
}
