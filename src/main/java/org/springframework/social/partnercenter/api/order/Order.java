package org.springframework.social.partnercenter.api.order;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.social.partnercenter.api.ResourceAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class Order {
	private String id;
	private String referenceCustomerId;
	private List<OrderLineItem> lineItems;
	private String status;
	private ZonedDateTime creationDate;
	private BillingCycle billingCycle;
	private ResourceAttributes attributes;

	public String getId() {
		return id;
	}

	public Order setId(String id) {
		this.id = id;
		return this;
	}

	public String getReferenceCustomerId() {
		return referenceCustomerId;
	}

	public Order setReferenceCustomerId(String referenceCustomerId) {
		this.referenceCustomerId = referenceCustomerId;
		return this;
	}

	public List<OrderLineItem> getLineItems() {
		return lineItems;
	}

	public Order setLineItems(List<OrderLineItem> lineItems) {
		this.lineItems = lineItems;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Order setStatus(String status) {
		this.status = status;
		return this;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public Order setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public Order setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
		return this;
	}

	public BillingCycle getBillingCycle() {
		return billingCycle;
	}

	public Order billingCycle(BillingCycle billingCycle) {
		this.billingCycle = billingCycle;
		return this;
	}

	@JsonIgnore
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String id;
		private String referenceCustomerId;
		private List<OrderLineItem> lineItems= new ArrayList<>();
		private String status;
		private ZonedDateTime creationDate;
		private BillingCycle billingCycle;
		private ResourceAttributes attributes;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder referenceCustomerId(String referenceCustomerId) {
			this.referenceCustomerId = referenceCustomerId;
			return this;
		}

		public Builder addLineItem(OrderLineItem lineItem){
			lineItem.setLineItemNumber(lineItems.stream().mapToInt(OrderLineItem::getLineItemNumber).max().orElse(-1) + 1);
			lineItems.add(lineItem);
			return this;
		}

		public Builder lineItems(List<OrderLineItem> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder creationDate(ZonedDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public Builder billingCycle(BillingCycle billingCycle) {
			this.billingCycle = billingCycle;
			return this;
		}

		public Builder attributes(ResourceAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public Order build() {
			Order order = new Order();
			order.attributes = attributes;
			order.billingCycle = billingCycle;
			order.creationDate = creationDate;
			order.id = id;
			order.lineItems = lineItems;
			order.referenceCustomerId = referenceCustomerId;
			order.status = status;
			return order;
		}
	}
}
