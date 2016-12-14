package org.springframework.social.partnercenter.model.response;

import java.util.List;
import java.util.Map;

import org.springframework.social.partnercenter.model.order.Link;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerCenterResponse<T> {
	@JsonProperty
	private int total;
	@JsonProperty
	private List<T> items;
	@JsonProperty
	private Map<String, Link> links;
	@JsonProperty
	private Map<String, String> attributes;

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

	public Map<String, Link> getLinks() {
		return links;
	}

	public PartnerCenterResponse setLinks(Map<String, Link> links) {
		this.links = links;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public PartnerCenterResponse setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
