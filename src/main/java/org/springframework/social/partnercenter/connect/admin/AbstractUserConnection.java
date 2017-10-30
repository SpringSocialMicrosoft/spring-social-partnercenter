package org.springframework.social.partnercenter.connect.admin;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.AbstractConnection;

public abstract class AbstractUserConnection<A> extends AbstractConnection<A> implements UserConnection<A> {
	public AbstractUserConnection(ApiAdapter<A> apiAdapter) {
		super(apiAdapter);
	}

	public AbstractUserConnection(ConnectionData data, ApiAdapter<A> apiAdapter) {
		super(data, apiAdapter);
	}
}
