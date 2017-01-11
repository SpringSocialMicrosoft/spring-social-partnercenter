package org.springframework.social.partnercenter.api.customer.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {
	@JsonProperty("Field")
	private String field;
	@JsonProperty("Value")
	private String value;
	@JsonProperty("Operator")
	private Operator operator;

	public String getField() {
		return field;
	}

	public Filter setField(String field) {
		this.field = field;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Filter setValue(String value) {
		this.value = value;
		return this;
	}

	public int getOperator() {
		return operator.toOrdinalValue();
	}

	public Filter setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}

	public static FilterBuilder builder(){
		return new FilterBuilder();
	}

	public static class FilterBuilder {
		private Operator operator;
		private String value;
		private String field;


		public FilterBuilder field(String field){
			this.field = field;
			return this;
		}

		public FilterBuilder operator(Operator operator){
			this.operator = operator;
			return this;
		}

		public FilterBuilder value(String value){
			this.value = value;
			return this;
		}

		public Filter build(){
			return new Filter().setValue(value).setOperator(operator).setField(field);
		}
	}
}
