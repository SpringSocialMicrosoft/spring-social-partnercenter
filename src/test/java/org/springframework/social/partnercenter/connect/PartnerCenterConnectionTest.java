package org.springframework.social.partnercenter.connect;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.security.AzureADAuthOperations;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PartnerCenterConnectionTest {

    @Mock
    private PartnerCenterServiceProvider partnerCenterServiceProvider;

    @Mock
    private ApiAdapter<PartnerCenter> apiAdapter;

    @Mock
    private AzureADAuthOperations azureADAuthOperations;

    private PartnerCenterConnection partnerCenterConnection;
    private final String accessToken = "accessToken";
    private final String refreshToken = "refreshToken";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        partnerCenterConnection = new PartnerCenterConnection("providerId", "providerUserId", accessToken, refreshToken, 10000L, partnerCenterServiceProvider, apiAdapter);
    }

    @Test
    public void refresh() {
        String newAccessToken = "newAccessToken";
        String newScope = "newScope";
        String newRefreshToken = "newRefreshToken";
        AccessGrant accesGrant = new AccessGrant(newAccessToken, newScope, newRefreshToken, 10000L);

        when(partnerCenterServiceProvider.getAzureADAuthOperations()).thenReturn(azureADAuthOperations);
        when(azureADAuthOperations.refreshAccess(null)).thenReturn(accesGrant);
        partnerCenterConnection.refresh();

        assertEquals(partnerCenterConnection.createData().getAccessToken(), newAccessToken);
        assertEquals(partnerCenterConnection.createData().getRefreshToken(), newRefreshToken);
    }

    @Test
    public void getApi() {
        assertEquals(partnerCenterConnection.createData().getAccessToken(), accessToken);
        assertEquals(partnerCenterConnection.createData().getRefreshToken(), refreshToken);
    }
}
