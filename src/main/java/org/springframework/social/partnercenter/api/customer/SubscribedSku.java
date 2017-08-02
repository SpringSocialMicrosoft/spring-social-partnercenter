package org.springframework.social.partnercenter.api.customer;

import java.util.List;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.customer.user.ProductSku;
import org.springframework.social.partnercenter.api.customer.user.ServicePlan;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubscribedSku {
	private Integer availableUnits;
	private Integer activeUnits;
	private Integer consumedUnits;
	private Integer suspendedUnits;
	private Integer totalUnits;
	private Integer warningUnits;
	private ProductSku productSku;
	private ResourceAttributes attributes;
	private List<ServicePlan> servicePlans;

	public Integer getAvailableUnits() {
		return availableUnits;
	}

	public void setAvailableUnits(Integer availableUnits) {
		this.availableUnits = availableUnits;
	}

	public Integer getActiveUnits() {
		return activeUnits;
	}

	public void setActiveUnits(Integer activeUnits) {
		this.activeUnits = activeUnits;
	}

	public Integer getConsumedUnits() {
		return consumedUnits;
	}

	public void setConsumedUnits(Integer consumedUnits) {
		this.consumedUnits = consumedUnits;
	}

	public Integer getSuspendedUnits() {
		return suspendedUnits;
	}

	public void setSuspendedUnits(Integer suspendedUnits) {
		this.suspendedUnits = suspendedUnits;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}

	public Integer getWarningUnits() {
		return warningUnits;
	}

	public void setWarningUnits(Integer warningUnits) {
		this.warningUnits = warningUnits;
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

	public List<ServicePlan> getServicePlans() {
		return servicePlans;
	}

	public void setServicePlans(List<ServicePlan> servicePlans) {
		this.servicePlans = servicePlans;
	}

	@JsonIgnore
	public static Builder builder(){
		return new Builder();
	}
	public static class Builder {
		private Integer availableUnits;
		private Integer activeUnits;
		private Integer consumedUnits;
		private Integer suspendedUnits;
		private Integer totalUnits;
		private Integer warningUnits;
		private ProductSku productSku;
		private ResourceAttributes attributes;
		private List<ServicePlan> servicePlans;

		public Builder availableUnits(Integer availableUnits) {
			this.availableUnits = availableUnits;
			return this;
		}

		public Builder activeUnits(Integer activeUnits) {
			this.activeUnits = activeUnits;
			return this;
		}

		public Builder consumedUnits(Integer consumedUnits) {
			this.consumedUnits = consumedUnits;
			return this;
		}

		public Builder suspendedUnits(Integer suspendedUnits) {
			this.suspendedUnits = suspendedUnits;
			return this;
		}

		public Builder totalUnits(Integer totalUnits) {
			this.totalUnits = totalUnits;
			return this;
		}

		public Builder warningUnits(Integer warningUnits) {
			this.warningUnits = warningUnits;
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

		public Builder servicePlans(List<ServicePlan> servicePlans) {
			this.servicePlans = servicePlans;
			return this;
		}

		public SubscribedSku build(){
			SubscribedSku subscribedSku = new SubscribedSku();
			subscribedSku.activeUnits = activeUnits;
			subscribedSku.attributes = attributes;
			subscribedSku.availableUnits = availableUnits;
			subscribedSku.consumedUnits = consumedUnits;
			subscribedSku.productSku = productSku;
			subscribedSku.suspendedUnits = suspendedUnits;
			subscribedSku.servicePlans = servicePlans;
			subscribedSku.totalUnits = totalUnits;
			subscribedSku.warningUnits = warningUnits;
			return subscribedSku;
		}
	}
}
