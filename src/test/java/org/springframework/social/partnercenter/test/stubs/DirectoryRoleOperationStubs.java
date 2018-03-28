package org.springframework.social.partnercenter.test.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.social.partnercenter.test.Resource;

public final class DirectoryRoleOperationStubs {
	public static void given_RolesReturned_200_OK(String customerId) {
		stubFor(get(urlEqualTo("/v1/customers/".concat(customerId).concat("/directoryroles")))
				.willReturn(aResponse()
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withStatus(200)
						.withBody(Resource.parseFile("data/roles/get_roles_OK.json").getAsStringFlattenedString())));
	}

	public static void given_GetUserRoles_200_OK(String customerId, String userId) {
		stubFor(get(urlEqualTo(format("/v1/customers/{0}/users/{1}/directoryroles", customerId, userId)))
				.willReturn(aResponse()
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withStatus(200)
						.withBody(Resource.parseFile("data/roles/get_users_roles_OK.json").getAsStringFlattenedString())));
	}

	public static void given_GetUserMembersWithRole_200_OK(String customerId, String roleId) {
		stubFor(get(urlEqualTo(format("/v1/customers/{0}/directoryroles/{1}/usermembers", customerId, roleId)))
				.willReturn(aResponse()
						.withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
						.withStatus(200)
						.withBody(Resource.parseFile("data/roles/get_members_with_role_OK.json").getAsStringFlattenedString())));
	}
}
