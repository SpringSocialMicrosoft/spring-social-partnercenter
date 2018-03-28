package org.springframework.social.partnercenter.test.stubs;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class StubURI {

	private static final String HOST_URI = "http://localhost";

	public static URI baseURI(int port) {
		return UriComponentsBuilder
				.fromUriString(HOST_URI)
				.port(port)
				.build()
				.toUri();
	}

	public static URI baseURI(int port, String... pathSegments) {
		return UriComponentsBuilder.fromUriString(HOST_URI)
				.port(port)
				.pathSegment(pathSegments)
				.build()
				.toUri();
	}

	public static URI customersBase(int port) {
		return baseURI(port, "v1", "customers");
	}
}
