package org.springframework.social.partnercenter.api.customer.user.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LicenseAssignment {
	private List<String> excludedPlans;
	private String skuId;

	public List<String> getExcludedPlans() {
		return excludedPlans;
	}

	public void setExcludedPlans(List<String> excludedPlans) {
		this.excludedPlans = excludedPlans;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	@JsonIgnore
	public static LicensesToAssignBuilder builder(){
		return new LicensesToAssignBuilder();
	}
	public static class LicensesToAssignBuilder{
		private List<String> excludedPlans;
		private String skuId;

		public LicensesToAssignBuilder excludedPlans(List<String> excludedPlans) {
			this.excludedPlans = excludedPlans;
			return this;
		}

		public LicensesToAssignBuilder skuId(String skuId) {
			this.skuId = skuId;
			return this;
		}

		public LicenseAssignment build(){
			LicenseAssignment licensesToAssign = new LicenseAssignment();
			licensesToAssign.setExcludedPlans(excludedPlans);
			licensesToAssign.setSkuId(skuId);
			return licensesToAssign;
		}
	}
}
