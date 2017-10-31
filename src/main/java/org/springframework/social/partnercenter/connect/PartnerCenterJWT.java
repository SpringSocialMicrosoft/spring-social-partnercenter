package org.springframework.social.partnercenter.connect;

import static org.springframework.social.partnercenter.serialization.Json.fromJson;

import java.util.Base64;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartnerCenterJWT {
	@JsonProperty("aud")
	private String audience;
	private String iss;
	private Integer iat;
	@JsonProperty("nbf")
	private Integer notBefore;
	@JsonProperty("exp")
	private Integer expiresOn;
	@JsonProperty("family_name")
	private String familyName;
	@JsonProperty("given_name")
	private String givenName;
	@JsonProperty("ipaddr")
	private String ipAddress;
	private String name;
	private UUID oid;
	private String sub;
	private UUID tid;
	@JsonProperty("unique_name")
	private String uniqueName;
	@JsonProperty("upn")
	private String upn;
	@JsonProperty("ver")
	private Double version;

	public static PartnerCenterJWT fromTokenString(String token) {
		return fromJson(new String(Base64.getDecoder().decode(token.split("\\.")[1])), PartnerCenterJWT.class);
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public Integer getIat() {
		return iat;
	}

	public void setIat(Integer iat) {
		this.iat = iat;
	}

	public Integer getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(Integer notBefore) {
		this.notBefore = notBefore;
	}

	public Integer getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Integer expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getOid() {
		return oid;
	}

	public void setOid(UUID oid) {
		this.oid = oid;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public UUID getTid() {
		return tid;
	}

	public void setTid(UUID tid) {
		this.tid = tid;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public String getUpn() {
		return upn;
	}

	public void setUpn(String upn) {
		this.upn = upn;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}
}
