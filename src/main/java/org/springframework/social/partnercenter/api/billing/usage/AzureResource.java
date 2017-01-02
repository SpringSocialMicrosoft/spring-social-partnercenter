package org.springframework.social.partnercenter.api.billing.usage;

public class AzureResource {
	private String id;
	private String name;
	private String category;
	private String subcategory;

	public String getId() {
		return id;
	}

	public AzureResource setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public AzureResource setName(String name) {
		this.name = name;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public AzureResource setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public AzureResource setSubcategory(String subcategory) {
		this.subcategory = subcategory;
		return this;
	}
}
