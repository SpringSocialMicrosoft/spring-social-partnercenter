package org.springframework.social.partnercenter.connect.admin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.admin.PartnerCenterAdmin;
import org.springframework.social.partnercenter.connect.PartnerCenterConnection;
import org.springframework.social.partnercenter.connect.PartnerCenterServiceProvider;

public class PartnerCenterAdminConnection extends PartnerCenterConnection {

	private String accessToken;
	private Long expireTime;
	private String username;
	private String password;
	private transient final PartnerCenterServiceProvider serviceProvider;

	private transient PartnerCenterAdmin adminApi;

	private transient PartnerCenterAdmin adminApiProxy;

	public PartnerCenterAdminConnection(String providerId, String providerUserId, String username, String password, String accessToken, Long expireTime, PartnerCenterServiceProvider serviceProvider, ApiAdapter<PartnerCenter> apiAdapter) {
		super(providerId, providerUserId, accessToken, "", expireTime, serviceProvider, apiAdapter);
		this.serviceProvider = serviceProvider;
		initAccessTokens(accessToken, expireTime, username, password);
		initApi();
		initApiProxy();
		initKey(providerId, providerUserId);
	}

	private void initApi() {
		adminApi = serviceProvider.getAdminApi(accessToken);
	}

	public void refresh() {
		synchronized (getMonitor()) {
			AccessGrant accessGrant = serviceProvider.getOAuthOperations().exchangeCredentialsForAccess(username, password, new OAuth2Parameters());
			initAccessTokens(accessGrant.getAccessToken(), accessGrant.getExpireTime(), this.username, this.password);
			initApi();
		}
	}

	public PartnerCenterAdmin getAdminApi() {
		if (adminApiProxy != null) {
			return adminApiProxy;
		} else {
			synchronized (getMonitor()) {
				return adminApi;
			}
		}
	}

	@Override
	public boolean hasExpired() {
		synchronized (getMonitor()) {
			return expireTime != null && System.currentTimeMillis() - 1000 >= expireTime;
		}
	}

	private void initAccessTokens(String accessToken, Long expireTime, String username, String password) {
		this.accessToken = accessToken;
		this.expireTime = expireTime;
		this.username = username;
		this.password = password;
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

