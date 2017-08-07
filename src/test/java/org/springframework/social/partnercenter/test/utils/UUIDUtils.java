package org.springframework.social.partnercenter.test.utils;

import java.util.UUID;

public class UUIDUtils {

	public static UUID createUUID(int id) {
		return UUID.fromString(String.format("00000000-0000-0000-0000-%012d", id));
	}

	public static String createUUIDAsString(int id) {
		return createUUID(id).toString();
	}
}
