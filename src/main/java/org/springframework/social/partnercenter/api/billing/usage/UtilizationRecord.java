package org.springframework.social.partnercenter.api.billing.usage;

import java.util.Map;

public class UtilizationRecord {
	private String usageStartTime;
	private String usageEndTime;
	private AzureResource resource;
	private double quantity;
	private String unit;
	private InfoFields infoFields;
	private Map<String, String> attributes;

}
