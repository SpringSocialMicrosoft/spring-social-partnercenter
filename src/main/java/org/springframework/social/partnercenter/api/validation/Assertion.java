package org.springframework.social.partnercenter.api.validation;

import static java.text.MessageFormat.format;

import org.springframework.util.Assert;

public class Assertion {
	public static void notNull(Object param, String argName) {
		Assert.notNull(param, format("[Assertion failed] - {0} argument must not be null", argName));
	}
}
