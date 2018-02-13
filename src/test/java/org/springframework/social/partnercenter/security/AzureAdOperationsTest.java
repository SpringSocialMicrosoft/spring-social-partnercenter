package org.springframework.social.partnercenter.security;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.social.partnercenter.test.Resource.parseFile;
import static org.springframework.social.partnercenter.test.stubs.AuthOperationSubs.given_401_unauthorized;
import static org.springframework.social.partnercenter.test.utils.UUIDUtils.createUUIDAsString;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.api.ApiAuthorizationException;
import org.springframework.social.partnercenter.api.AuthorizationFault;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class AzureAdOperationsTest {
	public static final String DOMAIN = "test.onmicrosoft.com";
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	@Test
	public void testPostForAccessGrantWithHeaders_whenExceptionOccurs_thenThrowAuthorizationFault() {
		given_401_unauthorized(DOMAIN);
		final AuthorizationFault jsonAsObject = parseFile("data/auth/401.json").getJsonAsObject(AuthorizationFault.class);

		AzureADAuthTemplate template = new AzureADAuthTemplate(createUUIDAsString(1), createUUIDAsString(2), createUUIDAsString(3), DOMAIN, "http://localhost:8080/oauth", "http://resource.com", "http://service.com");


		assertThatThrownBy(() -> template.postForAccessGrant("http://localhost:8080/oauth/test.onmicrosoft.com/oauth2/token", new HttpHeaders(), new OAuth2Parameters()))
				.isInstanceOf(ApiAuthorizationException.class)
				.hasMessage(jsonAsObject.getErrorDescription());
	}

	@Test
	public void testPostForAccessGrant_whenExceptionOccurs_thenThrowAuthorizationFault() {
		given_401_unauthorized(DOMAIN);
		final AuthorizationFault jsonAsObject = parseFile("data/auth/401.json").getJsonAsObject(AuthorizationFault.class);

		AzureADAuthTemplate template = new AzureADAuthTemplate(createUUIDAsString(1), createUUIDAsString(2), createUUIDAsString(3), DOMAIN, "http://localhost:8080/oauth", "http://resource.com", "http://service.com");


		assertThatThrownBy(() -> template.postForAccessGrant("http://localhost:8080/oauth/test.onmicrosoft.com/oauth2/token", new OAuth2Parameters()))
				.isInstanceOf(ApiAuthorizationException.class)
				.hasMessage(jsonAsObject.getErrorDescription());
	}

	@Test
	public void testPostForADToken_whenExceptionOccurs_thenThrowAuthorizationFault() {
		given_401_unauthorized(DOMAIN);
		final AuthorizationFault jsonAsObject = parseFile("data/auth/401.json").getJsonAsObject(AuthorizationFault.class);

		AzureADAuthTemplate template = new AzureADAuthTemplate(createUUIDAsString(1), createUUIDAsString(2), createUUIDAsString(3), DOMAIN, "http://localhost:8080/oauth", "http://resource.com", "http://service.com");


		assertThatThrownBy(template::postForADToken)
				.isInstanceOf(ApiAuthorizationException.class)
				.hasMessage(jsonAsObject.getErrorDescription());
	}
}
