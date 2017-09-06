package org.springframework.social.partnercenter.api;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerCenterResponse<T> {
	@JsonProperty("totalCount")
	private int total;
	@JsonProperty("continuationToken")
	private String continuationToken;
	@JsonProperty
	private List<T> items = new ArrayList<>();
	@JsonProperty
	private ResourceAttributes attributes;
	@JsonProperty
	private ResourceLinks links;

	public int getTotal() {
		return total;
	}

	public PartnerCenterResponse setTotal(int total) {
		this.total = total;
		return this;
	}

	public List<T> getItems() {
		return items;
	}

	public PartnerCenterResponse setItems(List<T> items) {
		this.items = items;
		return this;
	}

	public ResourceAttributes getAttributes() {
		return attributes;
	}

	public PartnerCenterResponse setAttributes(ResourceAttributes attributes) {
		this.attributes = attributes;
		return this;
	}

	public ResourceLinks getLinks() {
		return links;
	}

	public PartnerCenterResponse setLinks(ResourceLinks links) {
		this.links = links;
		return this;
	}

	public String getContinuationToken() {
		return continuationToken;
	}

	public PartnerCenterResponse setContinuationToken(String continuationToken) {
		this.continuationToken = continuationToken;
		return this;
	}

	public boolean hasNext(){
		return !isEmpty(links.getNext());
	}
}
