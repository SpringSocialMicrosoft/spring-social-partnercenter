package org.springframework.social.partnercenter.connect.admin;

import org.springframework.social.connect.Connection;

public interface UserConnection<A> extends Connection<A>{
	/**
	 * Refresh this connection.
	 * Used to renew an expired connection.
	 * If the refresh operation is successful, {@link #hasExpired()} returns false.
	 * Not supported by all connection implementations; if not supported, this method is a no-op.
	 * @param username username to use for authentication
	 * @param password password to be used for authentication
	 */
	void refresh(String username, String password);
}
