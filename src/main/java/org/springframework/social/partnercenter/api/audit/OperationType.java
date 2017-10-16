package org.springframework.social.partnercenter.api.audit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationType {
	UPDATE_CUSTOMER_QUALIFICATION("update_customer_qualification"),
	UPDATE_SUBSCRIPTION("update_subscription"),
	UPGRADE_SUBSCRIPTION("upgrade_subscription"),
	ADD_CUSTOMER("add_customer"),
	UPDATE_CUSTOMER_BILLING_PROFILE("update_customer_billing_profile"),
	UPDATE_CUSTOMER_SPENDING_BUDGET("update_customer_spending_budget"),
	DELETE_CUSTOMER("delete_customer"),
	CREATE_ORDER("create_order"),
	UPDATE_ORDER("update_order"),
	CREATE_CUSTOMER_USER("create_customer_user"),
	DELETE_CUSTOMER_USER("delete_customer_user"),
	UPDATE_CUSTOMER_USER("update_customer_user"),
	UPDATE_CUSTOMER_USER_LICENSES("update_customer_user_licenses"),
	RESET_CUSTOMER_USER_PASSWORD("reset_customer_user_password"),
	UPDATE_CUSTOMER_USER_PRINCIPAL_NAME("update_customer_user_principal_name"),
	RESTORE_CUSTOMER_USER("restore_customer_user");

	private String value;

	OperationType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public OperationType value(String value) {
		this.value = value;
		return this;
	}
}
