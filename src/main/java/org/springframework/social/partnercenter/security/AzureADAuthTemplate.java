package org.springframework.social.partnercenter.security;

import static java.util.Optional.ofNullable;
import static org.springframework.social.partnercenter.api.uri.UriProvider.DEFAULT_URL_PROVIDER;
import static org.springframework.util.Assert.notNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.partnercenter.api.ApiAuthorizationException;
import org.springframework.social.partnercenter.api.AuthorizationFault;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.logging.HttpRequestResponseLoggerFactory;
import org.springframework.social.partnercenter.http.logging.LogLevel;
import org.springframework.social.partnercenter.http.logging.LoggingRequestInterceptor;
import org.springframework.social.partnercenter.serialization.Json;
import org.springframework.social.partnercenter.serialization.JsonSerializationException;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.FormMapHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AzureADAuthTemplate implements AzureADAuthOperations {
	private final String webAppId;
	private final String nativeAppId;
	private final String webAppKey;
	private String accessTokenUrl;
	private String authorizeUrl;
	private RestTemplate restTemplate;
	private boolean useParametersForClientAuthentication;
	private final UriProvider uriProvider;

	/**
	 * Constructs an OAuth2Template for a given set of client credentials.
	 * @param webAppId the application ID
	 * @param webAppKey the application secret
	 * @param domain the reseller domain
	 * @param nativeAppId the client id for login with credentials
	 * @param authority domain of the reseller including .onmicrosoft.com
	 * @param resourceUrl see Partner Center documentation
	 * @param partnerServiceApiRoot root of the Partner Center API
	 */
	public AzureADAuthTemplate(String webAppId, String webAppKey, String nativeAppId, String domain, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(webAppId,
				webAppKey,
				nativeAppId,
				domain,
				UriProvider.builder()
						.authority(authority)
						.partnerServiceApiRoot(partnerServiceApiRoot)
						.resourceUrl(resourceUrl)
						.build());
	}
	/**
	 * Constructs an OAuth2Template for a given set of client credentials.
	 * @param webAppId the application ID
	 * @param webAppKey the application secret
	 * @param domain the reseller domain
	 * @param nativeAppId the client id for login with credentials
	 */
	public AzureADAuthTemplate(String webAppId, String webAppKey, String nativeAppId, String domain){
		this(webAppId,
				webAppKey,
				nativeAppId,
				domain,
				DEFAULT_URL_PROVIDER);
	}
	/**
	 * Constructs an OAuth2Template for a given set of client credentials.
	 * @param webAppId the application ID
	 * @param webAppKey the application secret
	 * @param domain the reseller domain
	 * @param nativeAppId the client id for login with credentials
	 * @param restTemplate restTemplate to make auth requests
	 */
	public AzureADAuthTemplate(String webAppId, String webAppKey, String nativeAppId, String domain, RestTemplate restTemplate){
		this(webAppId,
				webAppKey,
				nativeAppId,
				domain,
				DEFAULT_URL_PROVIDER);
		this.restTemplate = configureRestTemplate(restTemplate);
	}

	/**
	 * Constructs an OAuth2Template for a given set of client credentials.
	 * @param webAppId the Web App 'App ID'
	 * @param webAppKey the Web App 'Key'
	 * @param domain the reseller domain
	 * @param nativeAppId the Native App 'App ID'
	 * @param uriProvider builds auth urls
	 */
	public AzureADAuthTemplate(String webAppId, String webAppKey, String nativeAppId, String domain, UriProvider uriProvider) {
		notNull(webAppId, "The webAppId property cannot be null");
		notNull(webAppKey, "The webAppKey property cannot be null");
		notNull(nativeAppId, "The nativeAppId property cannot be null");
		notNull(domain, "The authorizeUrl property cannot be null");
		this.uriProvider = ofNullable(uriProvider).orElse(DEFAULT_URL_PROVIDER);
		this.webAppId = webAppId;
		this.webAppKey = webAppKey;
		this.nativeAppId = nativeAppId;
		this.authorizeUrl = uriProvider.buildPartnerCenterOAuth2Uri(domain);
		this.useParametersForClientAuthentication = true;
		this.accessTokenUrl = uriProvider.buildPartnerCenterTokenUri();
	}

	@Override
	public AccessGrant exchangeForAccess(){
		AzureADSecurityToken azureADSecurityToken = postForADToken();
		return exchangeForAccess(azureADSecurityToken.getAccessToken(), null);
	}

	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, MultiValueMap<String, String> additionalParameters) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "  + authorizationCode);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("grant_type", "jwt_token");
		if (additionalParameters != null) {
			params.putAll(additionalParameters);
		}
		try {
			return postForAccessGrant(accessTokenUrl, headers, params);
		} catch (HttpClientErrorException e){
			throw buildAuthFault(e);
		}
	}

	/**
	 * Creates the {@link RestTemplate} used to communicate with the provider's OAuth 2 API.
	 * This implementation creates a RestTemplate with a minimal set of HTTP message converters ({@link FormHttpMessageConverter} and {@link MappingJackson2HttpMessageConverter}).
	 * May be overridden to customize how the RestTemplate is created.
	 * For example, if the provider returns data in some format other than JSON for form-encoded, you might override to register an appropriate message converter.
	 * @return a {@link RestTemplate} used to communicate with the provider's OAuth 2 API
	 */
	protected RestTemplate createRestTemplate() {
		ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return configureRestTemplate(restTemplate);
	}

	private RestTemplate configureRestTemplate(RestTemplate restTemplate) {
		ArrayList<HttpMessageConverter<?>> converters = new ArrayList<>(3);
		converters.add(new FormHttpMessageConverter());
		converters.add(new FormMapHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}

	protected RestTemplate getRestTemplate() {
		// Lazily create RestTemplate to make sure all parameters have had a chance to be set.
		// Can't do this InitializingBean.afterPropertiesSet() because it will often be created directly and not as a bean.
		if (restTemplate == null) {
			restTemplate = createRestTemplate();
		}
		return restTemplate;
	}

	@Override
	public AccessGrant exchangeCredentialsForAccess(String username, String password, MultiValueMap<String, String> additionalParameters) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		if (useParametersForClientAuthentication) {
			params.set("client_id", nativeAppId);
		}
		params.set("username", username);
		params.set("password", password);
		params.set("resource", uriProvider.getPartnerServiceApiRoot());
		params.set("scope", "openid");
		params.set("grant_type", PartnerCenterGrantType.PASSWORD.asString());

		ofNullable(additionalParameters).ifPresent(additionalParameterMap ->
				additionalParameterMap.forEach(params::put));

		final AccessGrant accessGrant = postForAccessGrant(authorizeUrl, params);
		return exchangeForAccess(accessGrant.getAccessToken(), null);
	}

	@Override
	public AccessGrant refreshAccess(MultiValueMap<String, String> additionalParameters) {
		AzureADSecurityToken azureADSecurityToken = postForADToken();
		return exchangeForAccess(azureADSecurityToken.getAccessToken(), additionalParameters);
	}

	@Override
	public void enableSlf4j(LogLevel logLevel) {
		if (!isSlf4jEnabled()) {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(factory);
			getRestTemplate().setRequestFactory(requestFactory);

			getRestTemplate().getInterceptors()
					.add(new LoggingRequestInterceptor(HttpRequestResponseLoggerFactory.createSlf4jAuthorizationLogger(getClass(), logLevel)));
		}
	}

	@Override
	public boolean isSlf4jEnabled() {
		return getRestTemplate().getInterceptors().stream()
				.anyMatch(i -> i.getClass().isInstance(HttpRequestResponseLoggerFactory.class));
	}

	/**
	 * Posts the request for an access grant to the provider.
	 * The default implementation uses RestTemplate to request the access token and expects a JSON response to be bound to a Map. The information in the Map will be used to create an {@link AccessGrant}.
	 * Since the OAuth 2 specification indicates that an access token response should be in JSON format, there's often no need to override this method.
	 * If all you need to do is capture provider-specific data in the response, you should override createAccessGrant() instead.
	 * However, in the event of a provider whose access token response is non-JSON, you may need to override this method to request that the response be bound to something other than a Map.
	 * For example, if the access token response is given as form-encoded, this method should be overridden to call RestTemplate.postForObject() asking for the response to be bound to a MultiValueMap (whose contents can then be used to create an AccessGrant).
	 * @param accessTokenUrl the URL of the provider's access token endpoint.
	 * @param parameters the parameters to post to the access token endpoint.
	 * @param headers http headers to be sent with access request
	 * @return the access grant.
	 */
	@SuppressWarnings("unchecked")
	protected AccessGrant postForAccessGrant(String accessTokenUrl, HttpHeaders headers, MultiValueMap<String, String> parameters) {
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
		return extractAccessGrant(getRestTemplate().postForObject(accessTokenUrl, request, Map.class));
	}

	/**
	 * Posts the request for an access grant to the provider.
	 * The default implementation uses RestTemplate to request the access token and expects a JSON response to be bound to a Map. The information in the Map will be used to create an {@link AccessGrant}.
	 * Since the OAuth 2 specification indicates that an access token response should be in JSON format, there's often no need to override this method.
	 * If all you need to do is capture provider-specific data in the response, you should override createAccessGrant() instead.
	 * However, in the event of a provider whose access token response is non-JSON, you may need to override this method to request that the response be bound to something other than a Map.
	 * For example, if the access token response is given as form-encoded, this method should be overridden to call RestTemplate.postForObject() asking for the response to be bound to a MultiValueMap (whose contents can then be used to create an AccessGrant).
	 * @param accessTokenUrl the URL of the provider's access token endpoint.
	 * @param parameters the parameters to post to the access token endpoint.
	 * @return the access grant.
	 */
	@SuppressWarnings("unchecked")
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		return extractAccessGrant(getRestTemplate().postForObject(accessTokenUrl, new HttpEntity<>(parameters, headers), Map.class));
	}

	private AzureADSecurityToken postForADToken(){
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("grant_type", PartnerCenterGrantType.CLIENT_CREDENTIALS.asString());
		params.set("client_id", webAppId);
		params.set("client_secret", webAppKey);
		params.set("resource", uriProvider.getResourceUri());
		try {
			return getRestTemplate().postForObject(authorizeUrl, params, AzureADSecurityToken.class);
		} catch (HttpClientErrorException e){
			throw buildAuthFault(e);
		}
	}

	/**
	 * Creates an {@link AccessGrant} given the response from the access token exchange with the provider.
	 * May be overridden to create a custom AccessGrant that captures provider-specific information from the access token response.
	 * @param accessToken the access token value received from the provider
	 * @param scope the scope of the access token
	 * @param refreshToken a refresh token value received from the provider
	 * @param expiresIn the time (in seconds) remaining before the access token expires.
	 * @param idToken JWT for user
	 * @param response all parameters from the response received in the access token exchange.
	 * @return an {@link AccessGrant}
	 */
	protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String idToken, Map<String, Object> response) {
		return new PartnerCenterAccessGrant(accessToken, scope, refreshToken, idToken, expiresIn);
	}

	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		return createAccessGrant((String) result.get("access_token"), (String) result.get("scope"), (String) result.get("refresh_token"), getIntegerValue(result, "expires_in"), (String) result.get("id_token"), result);
	}

	private String formEncode(String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			// should not happen, UTF-8 is always supported
			throw new IllegalStateException(ex);
		}
	}

	// Retrieves object from map into an Integer, regardless of the object's actual type. Allows for flexibility in object type (eg, "3600" vs 3600).
	private Long getIntegerValue(Map<String, Object> map, String key) {
		try {
			return Long.valueOf(String.valueOf(map.get(key))); // normalize to String before creating integer value;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private ApiAuthorizationException buildAuthFault(HttpClientErrorException e){
		String responseBody = e.getResponseBodyAsString();
		try {
			AuthorizationFault authorizationFault = Json.fromJson(responseBody, AuthorizationFault.class);
			return new ApiAuthorizationException(authorizationFault.getErrorDescription(), e, authorizationFault);
		} catch (JsonSerializationException se){
			AuthorizationFault authorizationFault = new AuthorizationFault();
			authorizationFault.setErrorDescription(responseBody);
			return new ApiAuthorizationException(responseBody, e, authorizationFault);
		}
	}

	private PartnerCenterGrantType convertGrantType(GrantType grantType){
		return grantType.equals(GrantType.AUTHORIZATION_CODE) ? PartnerCenterGrantType.JWT_TOKEN : PartnerCenterGrantType.CLIENT_CREDENTIALS;
	}
}


