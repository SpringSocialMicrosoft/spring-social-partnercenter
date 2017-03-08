package org.springframework.social.partnercenter.http.client;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;

public class RestResourceTest {
	private final String RESPONSE_BODY = "{\"code\":20002,\"description\":\"Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416.\",\"data\":[\"Resource: test\"],\"source\":\"PartnerFD\"}";
	@Mock
	private RestTemplate restTemplate;
	private RestResource restResource;

	@Before
	public void init() {
		initMocks(this);
		restResource = new RestResource(restTemplate, "http://hello.com");
		doThrow(new HttpClientErrorException(BAD_REQUEST, BAD_REQUEST.getReasonPhrase(), RESPONSE_BODY.getBytes(Charsets.UTF_8), Charsets.UTF_8))
				.when(restTemplate).exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class));
	}

	@Test
	public void test() {
		assertThatThrownBy(() -> restResource.execute(URI.create("http://hello.com"), HttpMethod.GET, new HttpEntity<>(""), String.class))
				.isExactlyInstanceOf(ApiFaultException.class)
				.hasMessage("Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416.")
				.matches(o -> ((ApiFaultException)o).getErrorMessage().equals("Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416."))
				.matches(o -> ((ApiFaultException)o).getErrorCode().equals("20002"))
				.matches(o -> ((ApiFaultException)o).getAttributes().containsKey("Resource"))
				.matches(o -> ((ApiFaultException)o).getAttributes().containsValue("test"));
	}
}
