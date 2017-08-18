package org.springframework.social.partnercenter.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.partnercenter.PartnerCenter;

public interface RefreshableConnection<T> extends Connection<PartnerCenter> {
	void refresh();
	boolean hasExpired();
}
