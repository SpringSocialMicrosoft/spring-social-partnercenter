package org.springframework.social.partnercenter.http.logging;

import org.springframework.http.client.ClientHttpResponse;

public interface HttpBodyLogFormatter {
	String formatRequestLogs(byte[] body);
	String formatResponseLogs(ClientHttpResponse body);
}
