package org.springframework.social.partnercenter.api.customer.user.role;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.social.partnercenter.test.stubs.DirectoryRoleOperationStubs.given_GetUserMembersWithRole_200_OK;
import static org.springframework.social.partnercenter.test.stubs.DirectoryRoleOperationStubs.given_GetUserRoles_200_OK;
import static org.springframework.social.partnercenter.test.stubs.DirectoryRoleOperationStubs.given_RolesReturned_200_OK;
import static org.springframework.social.partnercenter.test.utils.UUIDUtils.createUUIDAsString;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.customer.user.UserMember;
import org.springframework.social.partnercenter.api.customer.user.role.impl.DirectoryRoleTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.stubs.StubURI;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class DirectoryRoleOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());
	private DirectoryRoleOperations directoryRoleOperations;


	@Before
	public void init() {
		directoryRoleOperations = new DirectoryRoleTemplate(new RestClient(TestRestTemplateFactory.createRestTemplate(), StubURI.baseURI(wireMockRule.port(), "v1", "customers")), true);
	}

	@Test
	public void getDirectoryRoles_whenCalled_MustReturnTheRolesCorrectly() {
		final String customerId = createUUIDAsString(1);
		given_RolesReturned_200_OK(customerId);

		final List<DirectoryRole> roles = directoryRoleOperations.getDirectoryRoles(customerId).getBody().getItems();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(roles).extracting("name").describedAs("Role Names").containsExactly("Company Administrator", "Helpdesk Administrator", "User Account Administrator", "Service Support Administrator");
			softly.assertThat(roles).extracting("id").describedAs("Role IDs").containsExactly("62e90394-69f5-4237-9190-012177145e10", "729827e3-9c14-49f7-bb1b-9608f156bbb8", "fe930be7-5e62-47db-91af-98c3a49a38b1", "f023fd81-a637-4b56-95fd-791ac0226033");
		});
	}

	@Test
	public void getUserRoles_whenUserHasRoles_thenRolesAreReturnedCorrectly() {
		final String customerId = createUUIDAsString(1);
		final String userId = createUUIDAsString(2);
		given_GetUserRoles_200_OK(customerId, userId);

		final List<DirectoryRole> roles = directoryRoleOperations.getUserRoles(customerId, userId).getBody().getItems();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(roles).extracting("name").describedAs("Role Name").containsExactly("Company Administrator");
			softly.assertThat(roles).extracting("id").describedAs("Role ID").containsExactly("62e90394-69f5-4237-9190-012177145e10");
		});
	}

	@Test
	public void getUserMembersWithRole_whenUserMemberHasRole_thenUserMemberIsReturnedCorrectly() {
		final String customerId = createUUIDAsString(1);
		final String roleId = "62e90394-69f5-4237-9190-012177145e10";
		given_GetUserMembersWithRole_200_OK(customerId, roleId);

		final List<UserMember> roles = directoryRoleOperations.getUserMembersWithRole(customerId, roleId).getBody().getItems();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(roles).extracting("displayName").describedAs("Display Name").containsExactly("1jonQ6HXb8NBAfbymfR7");
			softly.assertThat(roles).extracting("userPrincipalName").describedAs("User Principal Name").containsExactly("admin@1jonq6hxb8nbafbymfr7.onmicrosoft.com");
			softly.assertThat(roles).extracting("roleId").describedAs("Role ID").containsExactly("62e90394-69f5-4237-9190-012177145e10");
			softly.assertThat(roles).extracting("id").describedAs("User Member ID").containsExactly("705cd3c1-028f-41eb-9ffb-16ee8c5ae3af");
		});
	}
}