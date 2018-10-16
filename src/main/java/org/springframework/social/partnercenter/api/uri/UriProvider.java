package org.springframework.social.partnercenter.api.uri;

import static org.springframework.social.partnercenter.api.uri.SecurityRegion.DEU;
import static org.springframework.social.partnercenter.api.uri.SecurityRegion.USA;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class UriProvider {
	public static final UriProvider US = fromSecurityRegion(USA);
	public static final UriProvider DE = fromSecurityRegion(DEU);
	public static final UriProvider DEFAULT_URL_PROVIDER = UriProvider.US;

	private String authority;
	private String resourceUrl;
	private String partnerServiceApiRoot;
	private String domain;

	private UriProvider(String authority, String resourceUrl, String partnerServiceApiRoot) {
		this.authority = authority;
		this.resourceUrl = resourceUrl;
		this.partnerServiceApiRoot = partnerServiceApiRoot;
	}

	private UriProvider() {
	}

	public static UriProvider fromSecurityRegion(SecurityRegion region) {
		return UriProvider.builder()
				.authority(region.getAuthority())
				.resourceUrl(region.getResourceUrl())
				.partnerServiceApiRoot(region.getPartnerServiceApiRoot())
				.build();
	}

	public static UriProviderBuilder builder() {
		return new UriProviderBuilder();
	}

	public String buildPartnerCenterOAuth2Uri(String tenantId) {
		this.domain = tenantId;
		return UriComponentsBuilder.fromUriString(authority)
				.pathSegment(tenantId)
				.pathSegment("oauth2")
				.pathSegment("token")
				.build()
				.toString();
	}

	public String getAppResource() {
		return resourceUrl;
	}

	public String getUserPlusAppResource() {
		return partnerServiceApiRoot;
	}

	public String getPartnerServiceApiRoot() {
		return partnerServiceApiRoot;
	}

	public String getAuthority() {
		return authority;
	}

	public String buildPartnerCenterTokenUri() {
		return UriComponentsBuilder.fromUriString(partnerServiceApiRoot)
				.pathSegment("generatetoken")
				.build().toString();
	}

	public String buildAuthorizeUrl(String clientId, String redirectUri, String state) {
		final OAuth2Parameters parameters = new OAuth2Parameters(Stream.of(
				new AbstractMap.SimpleEntry<>("redirect_uri", Lists.newArrayList(redirectUri)),
				new AbstractMap.SimpleEntry<>("state", Lists.newArrayList(state)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
		return buildAuthorizeUrl(clientId, parameters);
	}

	public String buildAuthorizeUrl(String clientId, OAuth2Parameters parameters) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(authority).pathSegment("common", "oauth2", "authorize");

		final String clientSecretKey = "client_secret";
		if (parameters.containsKey(clientSecretKey)) {
			uriComponentsBuilder.queryParam(clientSecretKey, parameters.getFirst(clientSecretKey));
		}

		uriComponentsBuilder.queryParam("response_mode", "form_post")
				.queryParam("response_type", "code id_token")
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", parameters.getRedirectUri())
				.queryParam("nonce", UUID.randomUUID().toString())
				.queryParam("scope", "openid profile")
				.queryParam("state", parameters.getState());

		Optional.ofNullable(parameters).ifPresent(otherParams -> otherParams.entrySet().stream()
				.filter(entry -> !ImmutableList.of("client_id", "redirect_uri", "state", "scope", "client_secret").contains(entry.getKey()))
				.forEach(entry -> uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue())));

		return uriComponentsBuilder
				.build()
				.toString();
	}

	public UriComponentsBuilder partnerCenterInvoiceUri() {
		return partnerCenterBuilder().pathSegment("v1", "invoices");
	}

	public UriComponentsBuilder partnerCenterBuilder() {
		return UriComponentsBuilder.fromUriString(partnerServiceApiRoot);
	}

	public UriComponentsBuilder partnerCenterPricingUri() {
		return partnerCenterBuilder().pathSegment("v1", "ratecards", "azure");
	}

	public UriComponentsBuilder partnerCenterOfferUri() {
		return partnerCenterBuilder().pathSegment("v1", "offers");
	}

	public UriComponentsBuilder partnerCenterCustomerUri() {
		return partnerCenterBuilder().pathSegment("v1", "customers");
	}

	public UriComponentsBuilder partnerCenterRelationshipsUri() {
		return partnerCenterBuilder().pathSegment("v1", "relationships");
	}

	public UriComponentsBuilder partnerBaseUri() {
		return partnerCenterBuilder().pathSegment("v1");
	}

	public UriComponentsBuilder auditUri() {
		return partnerCenterBuilder().pathSegment("v1/auditrecords");
	}

	public UriComponentsBuilder partnerCenterProfileUri() {
		return partnerCenterBuilder().pathSegment("v1", "profiles");
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static class UriProviderBuilder {
		private String authority;
		private String resourceUrl;
		private String partnerServiceApiRoot;

		public UriProviderBuilder authority(String authority) {
			this.authority = authority;
			return this;
		}

		public UriProviderBuilder resourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
			return this;
		}

		public UriProviderBuilder partnerServiceApiRoot(String partnerServiceApiRoot) {
			this.partnerServiceApiRoot = partnerServiceApiRoot;
			return this;
		}

		public UriProvider build() {
			return new UriProvider(authority, resourceUrl, partnerServiceApiRoot);
		}
	}
}
