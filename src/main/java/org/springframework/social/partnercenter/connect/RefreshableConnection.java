package org.springframework.social.partnercenter.connect;

public interface RefreshableConnection {
	void refresh();
	boolean hasExpired();
}
