package org.springframework.social.partnercenter.api.customer.user;

import java.util.Map;

import org.springframework.social.partnercenter.api.customer.PasswordProfile;

public class CustomerUser {
	private String id;
	private String usageLocation;
	private String userPrincipalName;
	private String firstName;
	private String lastName;
	private String displayName;
	private PasswordProfile passwordProfile;
	private String lastDirectorySyncTime;
	private String userDomainType;
	private String state;
	private String softDeletionTime;
	private Map<String, String> attributes;
}
