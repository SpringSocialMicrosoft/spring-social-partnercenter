package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.uri.SecurityRegion;
import org.springframework.social.partnercenter.connect.admin.PartnerCenterAdminConnection;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

/**
 * @deprecated please use {@link PartnerCenterUserConnectionFactory} or {@link PartnerCenterApplicationConnectionFactory}
 */
@Deprecated
public class PartnerCenterConnectionFactory extends BasePartnerCenterConnectionFactory {

	public PartnerCenterConnectionFactory(String webAppId, String webAppKey, String tenant){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(webAppId, webAppKey, tenant), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String webAppId, String webAppKey, String tenant, SecurityRegion region){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(webAppId, webAppKey, tenant, region), new PartnerCenterApiAdapter());
	}

	@Deprecated
	public PartnerCenterConnectionFactory(String webAppId, String webAppKey, String nativeAppId, String tenant, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(webAppId, webAppKey, nativeAppId, tenant, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	public PartnerCenterConnectionFactory(String webAppId, String webAppKey, String tenant, String apiVersion, SecurityRegion region){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(webAppId, webAppKey, tenant, apiVersion, region), new PartnerCenterApiAdapter());
	}

	@Deprecated
	public PartnerCenterConnectionFactory(String webAppId, String webAppKey, String nativeAppId, String tenant, String apiVersion, String authority, String resourceUrl, String partnerServiceApiRoot){
		this(PartnerCenter.PROVIDER_ID, new PartnerCenterServiceProvider(webAppId, webAppKey, nativeAppId, tenant, apiVersion, authority, resourceUrl, partnerServiceApiRoot), new PartnerCenterApiAdapter());
	}

	private PartnerCenterConnectionFactory(String providerId, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, serviceProvider, apiAdapter);
	}

	public boolean canConnect(){
		try {
			createConnection().getApi().getCustomerOperations().getList(1);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public boolean canConnect(String username, String password){
		try {
			createConnection(username, password).getApi().getCustomerOperations().getList(1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public PartnerCenterConnection createConnection(){
		AccessGrant accessGrant = getAuthOperations().exchangeForAccess();
		return (PartnerCenterConnection) createConnection(accessGrant);
	}

	/**
	 * Create a OAuth2-based {@link Connection} from the {@link AccessGrant} returned after {@link #getAuthOperations() completing the OAuth2 flow}.
	 * @param accessGrant the access grant
	 * @return the new service provider connection
	 * @see OAuth2Operations#exchangeForAccess(String, String, org.springframework.util.MultiValueMap)
	 */
	public Connection<PartnerCenter> createConnection(AccessGrant accessGrant) {
		return new PartnerCenterConnection(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}

	public PartnerCenterAdminConnection createConnection(String username, String password){
		AccessGrant windowsLoginAccessGrant = getAuthOperations().exchangeCredentialsForAccess(username, password, new OAuth2Parameters());
		final AccessGrant partnerCenterGrant = getAuthOperations().exchangeForAccess(windowsLoginAccessGrant.getAccessToken(), null);

		return new PartnerCenterAdminConnection(getProviderId(), extractProviderUserId(windowsLoginAccessGrant), windowsLoginAccessGrant.getRefreshToken(), partnerCenterGrant.getAccessToken(),
				partnerCenterGrant.getExpireTime(), getPartnerCenterServiceProvider(), getApiAdapter());
	}
}
