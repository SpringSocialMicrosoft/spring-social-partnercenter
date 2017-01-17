package org.springframework.social.partnercenter.api.customer.user;

public class ProductSku {
	private String id;
	private String name;
	private String skuPartNumber;
	private String targetType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkuPartNumber() {
		return skuPartNumber;
	}

	public void setSkuPartNumber(String skuPartNumber) {
		this.skuPartNumber = skuPartNumber;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
}
