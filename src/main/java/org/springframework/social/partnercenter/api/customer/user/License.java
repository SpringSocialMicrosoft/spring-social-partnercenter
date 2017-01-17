package org.springframework.social.partnercenter.api.customer.user;

import java.util.Map;

public class License {
	private int availableUnits;
	private int activeUnits;
	private int consumedUnits;
	private int suspendedUnits;
	private int totalUnits;
	private int warningUnits;
	private String capabilityStatus;
	private Map<String, String> attributes;
}
