package org.springframework.social.partnercenter.api.consent;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.partnercenter.serialization.Json;
import org.springframework.social.partnercenter.test.utils.UUIDUtils;

public class ConsentTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void serializeConsent() {
        List<ApplicationGrant> applicationGrants = Arrays.asList(new ApplicationGrant(UUIDUtils.createUUIDAsString(2), Arrays.asList("scope1", "scope2")),
                new ApplicationGrant(UUIDUtils.createUUIDAsString(3), Arrays.asList("scope3")));
        Consent consent = new Consent("displayName", UUIDUtils.createUUIDAsString(1), applicationGrants);

        String consentJson = Json.toJson(consent);

        assertThat(consentJson).isEqualTo("{\"displayName\":\"displayName\",\"applicationId\":\"00000000-0000-0000-0000-000000000001\",\"applicationGrants\":[{\"enterpriseApplicationId\":\"00000000-0000-0000-0000-000000000002\",\"scope\":\"scope1,scope2\"},{\"enterpriseApplicationId\":\"00000000-0000-0000-0000-000000000003\",\"scope\":\"scope3\"}]}");

        assertThat(consent).isEqualTo(Json.fromJson(consentJson, Consent.class));

        System.out.println(consentJson);
    }
}