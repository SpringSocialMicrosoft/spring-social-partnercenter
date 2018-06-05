package org.springframework.social.partnercenter.api.query;

import org.springframework.social.partnercenter.api.validation.Assertion;
import org.springframework.social.partnercenter.serialization.Json;

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

	public static Filter of(String field, Operator operator, String value) {
		Assertion.notNull(field, "field");
		Assertion.notNull(operator, "operator");
		Assertion.notNull(value, "value");
		Filter filter = new Filter();
		filter.setField(field);
		filter.setOperator(operator);
		filter.setValue(value);
		return filter;
	}

	public static String create(SearchField field, Operator operator, String value) {
		return create(field.asString(), operator, value);
	}

	public static String create(String field, Operator operator, String value) {
		return of(field, operator, value).toJson();
	}

	public String toJson() {
		return Json.toJson(this);
	}

	/**
	 * @deprecated Please use {@link Filter#of(String, Operator, String)}. As all properties are required it makes more sense to only allow creation in ways that enforce this.
	 * @return a new {@link Filter}
	 */
	@Deprecated
	public static FilterBuilder builder(){
		return new FilterBuilder();
	}

	@Deprecated
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
