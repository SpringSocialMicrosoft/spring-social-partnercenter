package org.springframework.social.partnercenter.api.query;

public enum Operator {
	EQUALS(0),
	NOT_EQUALS(1),
	GREATER_THAN(2),
	GREATER_THAN_OR_EQUALS(3),
	LESS_THAN(4),
	LESS_THAN_OR_EQUALS(5),
	SUBSTRING(6),
	AND(7),
	OR(8),
	STARTS_WITH(9),
	NOT_STARTS_WITH(10);

	private int index;

	private Operator(int index){
		this.index = index;
	}

	public int toOrdinalValue(){
		return index;
	}
}
