package org.springframework.social.partnercenter.api.order.offer;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferCategory {
	private String id;
	private String name;
	private int rank;
	private String locale;
	private String country;
	private Map<String, String> attributes;

	public String getId() {
		return id;
	}

	public OfferCategory setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public OfferCategory setName(String name) {
		this.name = name;
		return this;
	}

	public int getRank() {
		return rank;
	}

	public OfferCategory setRank(int rank) {
		this.rank = rank;
		return this;
	}

	public String getLocale() {
		return locale;
	}

	public OfferCategory setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public OfferCategory setCountry(String country) {
		this.country = country;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public OfferCategory setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}
}
