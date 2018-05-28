package org.springframework.social.partnercenter.connect;

import java.util.Locale;

import org.springframework.social.partnercenter.PartnerCenter;

public interface LocalizedConnection {
	PartnerCenter getApi(Locale locale);
}
