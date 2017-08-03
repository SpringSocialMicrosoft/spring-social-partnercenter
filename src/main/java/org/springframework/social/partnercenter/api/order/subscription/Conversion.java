package org.springframework.social.partnercenter.api.order.subscription;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.ResourceAttributes;
import org.springframework.social.partnercenter.api.order.offer.BillingCycle;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class Conversion {
	private String offerId;
	private String targetOfferId;
	private String orderId;
	private Integer quantity;
	private BillingCycle billingCycle;
	private ResourceAttributes attributes = new ResourceAttributes(getClass().getSimpleName());

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTargetOfferId() {
		return targetOfferId;
	}

	public void setTargetOfferId(String targetOfferId) {
		this.targetOfferId = targetOfferId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BillingCycle getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(BillingCycle billingCycle) {
		this.billingCycle = billingCycle;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}
}
