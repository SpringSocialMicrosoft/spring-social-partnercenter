package org.springframework.social.partnercenter.connect.admin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.AbstractConnection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.PartnerCenterAdmin;
import org.springframework.social.partnercenter.security.PartnerCenterServiceProvider;

public class PartnerCenterAdminConnection extends AbstractConnection<PartnerCenter> {

	private String accessToken;
	private String refreshToken;
	private Long expireTime;
	private transient final PartnerCenterServiceProvider serviceProvider;

	private transient PartnerCenterAdmin adminApi;

	private transient PartnerCenterAdmin adminApiProxy;

	/**
	 * Creates a new {@link PartnerCenterAdminConnection} from the data provided.
	 * Designed to be called when re-constituting an existing {@link Connection} from {@link ConnectionData}.
	 * @param data the data holding the state of this connection
	 * @param serviceProvider the OAuth2-based ServiceProvider
	 * @param apiAdapter the ApiAdapter for the ServiceProvider
	 */
	public PartnerCenterAdminConnection(ConnectionData data, ApiAdapter<PartnerCenter> apiAdapter, PartnerCenterServiceProvider serviceProvider) {
		super(data, apiAdapter);
		this.serviceProvider = serviceProvider;
		initAccessAttributes(data.getAccessToken(), data.getExpireTime(), data.getRefreshToken());
		initApi();
		initApiProxy();
		initKey(data.getProviderId(), data.getProviderUserId());
		if (this.hasExpired()) {
			refresh();
		}
	}

	/**
	 * Creates a new {@link PartnerCenterAdminConnection} from the data provided.
	 * Designed to be called when re-constituting an existing {@link Connection} from {@link ConnectionData}.
	 * @param providerId partner-center
	 * @param providerUserId userId of the sign in user.
	 * @param refreshToken token used to refresh access when accessToken has expired
	 * @param accessToken token provided to Partner Center to authenticate user and client
	 * @param expireTime time at which access token will expire.
	 * @param serviceProvider the OAuth2-based ServiceProvider
	 * @param apiAdapter the ApiAdapter for the ServiceProvider
	 */
	public PartnerCenterAdminConnection(String providerId, String providerUserId, String refreshToken, String accessToken, Long expireTime, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(apiAdapter);
		this.serviceProvider = serviceProvider;
		initAccessAttributes(accessToken, expireTime, refreshToken);
		initApi();
		initApiProxy();
		initKey(providerId, providerUserId);
	}

	private void initApi() {
		adminApi = serviceProvider.getAdminApi(accessToken);
	}

	@Override
	public void refresh() {
		synchronized (getMonitor()) {
			AccessGrant accessGrant = serviceProvider.getAzureADAuthOperations().refreshAccess(refreshToken, new OAuth2Parameters());
			initAccessAttributes(accessGrant.getAccessToken(), accessGrant.getExpireTime(), accessGrant.getRefreshToken());
			initApi();
		}
	}

	@Override
	public PartnerCenterAdmin getApi() {
		if (adminApiProxy != null) {
			return adminApiProxy;
		} else {
			synchronized (getMonitor()) {
				return adminApi;
			}
		}
	}

	@Override
	public ConnectionData createData() {
		synchronized (getMonitor()) {
			return new ConnectionData(getKey().getProviderId(), getKey().getProviderUserId(), getDisplayName(), getProfileUrl(), getImageUrl(), accessToken, null, refreshToken, expireTime);
		}
	}

	@Override
	public boolean hasExpired() {
		synchronized (getMonitor()) {
			return expireTime != null && System.currentTimeMillis() - 1000 >= expireTime;
		}
	}

	private void initAccessAttributes(String accessToken, Long expireTime, String refreshToken) {
		this.accessToken = accessToken;
		this.expireTime = expireTime;
		this.refreshToken = refreshToken;
	}


	private void initApiProxy() {
		adminApiProxy = (PartnerCenterAdmin) Proxy.newProxyInstance(PartnerCenterAdmin.class.getClassLoader(), new Class<?>[]{ PartnerCenterAdmin.class }, new PartnerCenterAdminConnection.AdminApiInvocationHandler());
	}

	private class AdminApiInvocationHandler implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			synchronized (getMonitor()) {
				if (hasExpired()) {
					throw new ExpiredAuthorizationException(getKey().getProviderId());
				}
				try {
					return method.invoke(PartnerCenterAdminConnection.this.adminApi, args);
				} catch (InvocationTargetException e) {
					throw e.getTargetException();
				}
			}
		}
	}
}

