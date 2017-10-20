package org.springframework.social.partnercenter.test.stubs;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class TestURIs {
	public static URI BASE_URI = URI.create("http://localhost:8080");
	public static URI baseURI(String... pathSegments) {
		return UriComponentsBuilder.fromHttpUrl(BASE_URI.toString())
				.pathSegment(pathSegments)
				.build().toUri();
	}
}
