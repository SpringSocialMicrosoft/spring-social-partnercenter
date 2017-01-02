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


	public enum Granularity{
		DAILY("daily"), HOURLY("hourly");

		private String value;

		public String value() {
			return value;
		}

		private Granularity(String value){
			this.value = value;
		}
	}
}
