package org.springframework.social.partnercenter.api.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Password {

	private Integer length;

	public Integer getLength() {
		return length;
	}

	@JsonProperty("length")
	public void setLength(Integer length) {
		this.length = length;
	}

}
