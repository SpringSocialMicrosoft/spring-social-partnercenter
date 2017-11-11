package org.springframework.social.partnercenter.api.customer.user;

import java.util.List;

import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class License {
	private List<ServicePlan> servicePlans;
	private ProductSku productSku;
	private ResourceAttributes attributes;

	public List<ServicePlan> getServicePlans() {
		return servicePlans;
	}

	public void setServicePlans(List<ServicePlan> servicePlans) {
		this.servicePlans = servicePlans;
	}

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}

	@JsonIgnore
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder {
		private List<ServicePlan> servicePlans;
		private ProductSku productSku;
		private ResourceAttributes attributes;

		public Builder servicePlans(List<ServicePlan> servicePlans) {
			this.servicePlans = servicePlans;
			return this;
		}

		public Builder productSku(ProductSku productSku) {
			this.productSku = productSku;
			return this;
		}

		public Builder attributes(ResourceAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public License build() {
			License license = new License();
			license.productSku = productSku;
			license.servicePlans = servicePlans;
			license.attributes = attributes;
			return license;
		}
	}
}
