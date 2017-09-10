package org.springframework.social.partnercenter.http.logging;

import org.springframework.http.HttpHeaders;

public interface HttpHeaderLogFormatter {
	String formatHeaderLogs(HttpHeaders headers);
}
