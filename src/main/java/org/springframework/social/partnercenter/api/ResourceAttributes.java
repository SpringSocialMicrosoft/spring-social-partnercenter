package org.springframework.social.partnercenter.api;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public class ResourceAttributes {
	private String etag;
	private String objectType;

	public ResourceAttributes(String etag, String objectType) {
		this.etag = etag;
		this.objectType = objectType;
	}

	public ResourceAttributes(String objectType) {
		this.objectType = objectType;
	}

	public ResourceAttributes() {
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
