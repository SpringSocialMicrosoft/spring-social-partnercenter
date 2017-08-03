package org.springframework.social.partnercenter.api.order.subscription;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.springframework.social.partnercenter.api.Link;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class SubscriptionLinks {
	private Link offer;
	private Link parentSubscription;
	private Link self;
	private Link next;
	private Link previous;

	public Link getOffer() {
		return offer;
	}

	public void setOffer(Link offer) {
		this.offer = offer;
	}

	public Link getParentSubscription() {
		return parentSubscription;
	}

	public void setParentSubscription(Link parentSubscription) {
		this.parentSubscription = parentSubscription;
	}

	public Link getSelf() {
		return self;
	}

	public void setSelf(Link self) {
		this.self = self;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public Link getPrevious() {
		return previous;
	}

	public void setPrevious(Link previous) {
		this.previous = previous;
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder {
		private Link offer;
		private Link parentSubscription;
		private Link self;
		private Link next;
		private Link previous;

		public Builder offer(Link offer) {
			this.offer = offer;
			return this;
		}

		public Builder parentSubscription(Link parentSubscription) {
			this.parentSubscription = parentSubscription;
			return this;
		}

		public Builder self(Link self) {
			this.self = self;
			return this;
		}

		public Builder next(Link next) {
			this.next = next;
			return this;
		}

		public Builder previous(Link previous) {
			this.previous = previous;
			return this;
		}

		public SubscriptionLinks build(){
			SubscriptionLinks links = new SubscriptionLinks();
			links.next = next;
			links.offer = offer;
			links.parentSubscription = parentSubscription;
			links.previous = previous;
			links.self = self;
			return links;
		}
	}
}
