package org.springframework.social.partnercenter.api.customer.user;

import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class License {
	private int availableUnits;
	private int activeUnits;
	private int consumedUnits;
	private int suspendedUnits;
	private int totalUnits;
	private int warningUnits;
	private String capabilityStatus;
	private ResourceAttributes attributes;

	public int getAvailableUnits() {
		return availableUnits;
	}

	public void setAvailableUnits(int availableUnits) {
		this.availableUnits = availableUnits;
	}

	public int getActiveUnits() {
		return activeUnits;
	}

	public void setActiveUnits(int activeUnits) {
		this.activeUnits = activeUnits;
	}

	public int getConsumedUnits() {
		return consumedUnits;
	}

	public void setConsumedUnits(int consumedUnits) {
		this.consumedUnits = consumedUnits;
	}

	public int getSuspendedUnits() {
		return suspendedUnits;
	}

	public void setSuspendedUnits(int suspendedUnits) {
		this.suspendedUnits = suspendedUnits;
	}

	public int getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}

	public int getWarningUnits() {
		return warningUnits;
	}

	public void setWarningUnits(int warningUnits) {
		this.warningUnits = warningUnits;
	}

	public String getCapabilityStatus() {
		return capabilityStatus;
	}

	public void setCapabilityStatus(String capabilityStatus) {
		this.capabilityStatus = capabilityStatus;
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
		private int availableUnits;
		private int activeUnits;
		private int consumedUnits;
		private int suspendedUnits;
		private int totalUnits;
		private int warningUnits;
		private String capabilityStatus;
		private ResourceAttributes attributes;

		public Builder availableUnits(int availableUnits) {
			this.availableUnits = availableUnits;
			return this;
		}

		public Builder activeUnits(int activeUnits) {
			this.activeUnits = activeUnits;
			return this;
		}

		public Builder consumedUnits(int consumedUnits) {
			this.consumedUnits = consumedUnits;
			return this;
		}

		public Builder suspendedUnits(int suspendedUnits) {
			this.suspendedUnits = suspendedUnits;
			return this;
		}

		public Builder totalUnits(int totalUnits) {
			this.totalUnits = totalUnits;
			return this;
		}

		public Builder warningUnits(int warningUnits) {
			this.warningUnits = warningUnits;
			return this;
		}

		public Builder capabilityStatus(String capabilityStatus) {
			this.capabilityStatus = capabilityStatus;
			return this;
		}

		public Builder attributes(ResourceAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public License build() {
			License license = new License();
			license.activeUnits = activeUnits;
			license.attributes = attributes;
			license.availableUnits = availableUnits;
			license.capabilityStatus = capabilityStatus;
			license.consumedUnits = consumedUnits;
			license.suspendedUnits = suspendedUnits;
			license.totalUnits = totalUnits;
			license.warningUnits = warningUnits;
			return license;
		}
	}
}
