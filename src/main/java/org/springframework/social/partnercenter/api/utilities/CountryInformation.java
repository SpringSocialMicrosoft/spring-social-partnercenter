package org.springframework.social.partnercenter.api.utilities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryInformation {
	private String iso2Code;
	private String defaultCulture;
	private boolean isStateRequired;
	private List<String> supportedStatesList;
	private List<String> supportedLanguagesList;
	private List<String> supportedCulturesList;
	private boolean isPostalCodeRequired;
	private String postalCodeRegex;
	private boolean isCityRequired;
	private boolean isVatIdSupported;
	private String taxIdFormat;
	private String taxIdSample;
	private String phoneNumberRegex;
	private boolean isRegistrationNumberSupported;
	private boolean isTaxIdSupported;
	private boolean isTaxIdOptional;
	private String resellerAgreementRegion;
	private String geographicRegion;
	private List<String> countryCallingCodesList;
	private Map<String, String> attributes;

	public String getIso2Code() {
		return iso2Code;
	}

	public CountryInformation setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
		return this;
	}

	public String getDefaultCulture() {
		return defaultCulture;
	}

	public CountryInformation setDefaultCulture(String defaultCulture) {
		this.defaultCulture = defaultCulture;
		return this;
	}

	public boolean isStateRequired() {
		return isStateRequired;
	}

	public CountryInformation setStateRequired(boolean stateRequired) {
		isStateRequired = stateRequired;
		return this;
	}

	public List<String> getSupportedStatesList() {
		return supportedStatesList;
	}

	public CountryInformation setSupportedStatesList(List<String> supportedStatesList) {
		this.supportedStatesList = supportedStatesList;
		return this;
	}

	public List<String> getSupportedLanguagesList() {
		return supportedLanguagesList;
	}

	public CountryInformation setSupportedLanguagesList(List<String> supportedLanguagesList) {
		this.supportedLanguagesList = supportedLanguagesList;
		return this;
	}

	public List<String> getSupportedCulturesList() {
		return supportedCulturesList;
	}

	public CountryInformation setSupportedCulturesList(List<String> supportedCulturesList) {
		this.supportedCulturesList = supportedCulturesList;
		return this;
	}

	public boolean isPostalCodeRequired() {
		return isPostalCodeRequired;
	}

	public CountryInformation setPostalCodeRequired(boolean postalCodeRequired) {
		isPostalCodeRequired = postalCodeRequired;
		return this;
	}

	public String getPostalCodeRegex() {
		return postalCodeRegex;
	}

	public CountryInformation setPostalCodeRegex(String postalCodeRegex) {
		this.postalCodeRegex = postalCodeRegex;
		return this;
	}

	public boolean isCityRequired() {
		return isCityRequired;
	}

	public CountryInformation setCityRequired(boolean cityRequired) {
		isCityRequired = cityRequired;
		return this;
	}

	public boolean isVatIdSupported() {
		return isVatIdSupported;
	}

	public CountryInformation setVatIdSupported(boolean vatIdSupported) {
		isVatIdSupported = vatIdSupported;
		return this;
	}

	public String getTaxIdFormat() {
		return taxIdFormat;
	}

	public CountryInformation setTaxIdFormat(String taxIdFormat) {
		this.taxIdFormat = taxIdFormat;
		return this;
	}

	public String getTaxIdSample() {
		return taxIdSample;
	}

	public CountryInformation setTaxIdSample(String taxIdSample) {
		this.taxIdSample = taxIdSample;
		return this;
	}

	public String getPhoneNumberRegex() {
		return phoneNumberRegex;
	}

	public CountryInformation setPhoneNumberRegex(String phoneNumberRegex) {
		this.phoneNumberRegex = phoneNumberRegex;
		return this;
	}

	public boolean isRegistrationNumberSupported() {
		return isRegistrationNumberSupported;
	}

	public CountryInformation setRegistrationNumberSupported(boolean registrationNumberSupported) {
		isRegistrationNumberSupported = registrationNumberSupported;
		return this;
	}

	public boolean isTaxIdSupported() {
		return isTaxIdSupported;
	}

	public CountryInformation setTaxIdSupported(boolean taxIdSupported) {
		isTaxIdSupported = taxIdSupported;
		return this;
	}

	public String getResellerAgreementRegion() {
		return resellerAgreementRegion;
	}

	public CountryInformation setResellerAgreementRegion(String resellerAgreementRegion) {
		this.resellerAgreementRegion = resellerAgreementRegion;
		return this;
	}

	public String getGeographicRegion() {
		return geographicRegion;
	}

	public CountryInformation setGeographicRegion(String geographicRegion) {
		this.geographicRegion = geographicRegion;
		return this;
	}

	public List<String> getCountryCallingCodesList() {
		return countryCallingCodesList;
	}

	public CountryInformation setCountryCallingCodesList(List<String> countryCallingCodesList) {
		this.countryCallingCodesList = countryCallingCodesList;
		return this;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public CountryInformation setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		return this;
	}

	public boolean isTaxIdOptional() {
		return isTaxIdOptional;
	}

	public CountryInformation setTaxIdOptional(boolean taxIdOptional) {
		isTaxIdOptional = taxIdOptional;
		return this;
	}
}
