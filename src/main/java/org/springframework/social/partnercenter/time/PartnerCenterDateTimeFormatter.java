package org.springframework.social.partnercenter.time;

import java.time.format.DateTimeFormatter;

public class PartnerCenterDateTimeFormatter {
	public static DateTimeFormatter PARTNER_CENTER_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX");
	public static DateTimeFormatter PARTNER_CENTER_SHORT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
