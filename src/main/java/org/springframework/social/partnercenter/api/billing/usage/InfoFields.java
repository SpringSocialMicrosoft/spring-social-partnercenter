package org.springframework.social.partnercenter.api.billing.usage;

public class InfoFields {
	private String meteredRegion;
	private String meteredService;
	private String meteredServiceType;
	private String project;

	public String getMeteredRegion() {
		return meteredRegion;
	}

	public InfoFields setMeteredRegion(String meteredRegion) {
		this.meteredRegion = meteredRegion;
		return this;
	}

	public String getMeteredService() {
		return meteredService;
	}

	public InfoFields setMeteredService(String meteredService) {
		this.meteredService = meteredService;
		return this;
	}

	public String getMeteredServiceType() {
		return meteredServiceType;
	}

	public InfoFields setMeteredServiceType(String meteredServiceType) {
		this.meteredServiceType = meteredServiceType;
		return this;
	}

	public String getProject() {
		return project;
	}

	public InfoFields setProject(String project) {
		this.project = project;
		return this;
	}
}
