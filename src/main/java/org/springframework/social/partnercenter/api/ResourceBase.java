package org.springframework.social.partnercenter.api;

public abstract class ResourceBase {
	private ResourceAttributes attributes;

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
	}
}
