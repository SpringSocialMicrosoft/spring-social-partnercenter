package org.springframework.social.partnercenter.http.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.net.URI;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.ApiFaultException;
import org.springframework.social.partnercenter.http.PartnerCenterHttpHeaders;
import org.springframework.social.partnercenter.http.client.retry.RetryBuilder;
import org.springframework.social.partnercenter.test.utils.UUIDUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import com.google.common.base.Charsets;

public class RestResourceTest {
	public static final String REQUEST_ID = "requestId";
	public static final String CORRELATION_ID = "correlationId";
	@Mock
	private RestOperations restOperations;
	@Captor
	private ArgumentCaptor<HttpMethod> httpMethodArgumentCaptor;
	@Captor
	private ArgumentCaptor<HttpEntity> httpEntityArgumentCaptor;
	@Captor
	private ArgumentCaptor<URI> uriArgumentCaptor;

	@Before
	public void init() {
		initMocks(this);
	}

	@Test
	public void testApiFaultException() {
		RestClient restResource = new RestClient(restOperations, URI.create("http://hello.com"));
		String RESPONSE_BODY = "{\"code\":20002,\"description\":\"Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416.\",\"data\":[\"Resource: test\"],\"source\":\"PartnerFD\"}";
		doThrow(new HttpClientErrorException(BAD_REQUEST, BAD_REQUEST.getReasonPhrase(), RESPONSE_BODY.getBytes(Charsets.UTF_8), Charsets.UTF_8))
				.when(restOperations).exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class));

		assertThatThrownBy(() -> restResource.execute(URI.create("http://hello.com"), HttpMethod.GET, new HttpEntity<>(""), String.class))
				.isExactlyInstanceOf(ApiFaultException.class)
				.hasMessage("Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416.")
				.matches(o -> ((ApiFaultException)o).getErrorMessage().equals("Reseller CAID fc17d052-b0b2-40ca-af51-e203877acda0 does not have a reseller relationship with customer CAID 7e2e644f-6000-465e-9056-4fdad81bf416."))
				.matches(o -> ((ApiFaultException)o).getErrorCode().equals("20002"))
				.matches(o -> ((ApiFaultException)o).getAttributes().containsKey("Resource"))
				.matches(o -> ((ApiFaultException)o).getAttributes().containsValue("test"));
	}

	@Test
	public void test2(){
		assertThat(createRestResource().request()).isInstanceOf(HttpRequestBuilder.class);
	}

	@Test
	public void testGet(){
		doReturn(ResponseEntity.ok("Hello"))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		createBasicRequest().get(String.class);

		validateRequest(HttpMethod.GET, new HttpEntity<>(createExpectedHeaders()));

	}

	@Test
	public void testPost(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(TestResponse.class));

		final TestRequest request = TestRequest.builder().name("John Lennon").build();
		createBasicRequest().post(request, TestResponse.class);

		validateRequest(HttpMethod.POST, new HttpEntity<>(request, createExpectedHeaders()));
	}

	@Test
	public void testPut(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(TestResponse.class));

		final TestRequest request = TestRequest.builder().name("John Lennon").build();
		createBasicRequest().put(request, TestResponse.class);

		validateRequest(HttpMethod.PUT, new HttpEntity<>(request, createExpectedHeaders()));
	}

	@Test
	public void testPatch(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(TestResponse.class));

		final TestRequest request = TestRequest.builder().name("John Lennon").build();
		createBasicRequest().patch(request, TestResponse.class);

		validateRequest(HttpMethod.PATCH, new HttpEntity<>(request, createExpectedHeaders()));
	}

	@Test
	public void testDelete(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		createBasicRequest().delete();

		validateRequest(HttpMethod.DELETE, new HttpEntity<>(createExpectedHeaders()));
	}

	@Test
	public void testHead(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(Void.class));

		createBasicRequest().header("test", "header").head();

		final HttpHeaders headers = createExpectedHeaders();
		headers.add("test", "header");
		validateRequest(HttpMethod.HEAD, new HttpEntity<>(headers));
	}

	@Test
	public void testGet_whenUsingParameterizedTypeReferenceForDefiningResponseType_thenResponseIdReturnedCorrectly(){
		doReturn(ResponseEntity.ok(TestResponse.builder().id(UUIDUtils.createUUIDAsString(1))))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(new ParameterizedTypeReference<List<TestResponse>>(){}));

		createBasicRequest().get(new ParameterizedTypeReference<List<TestResponse>>(){});

		validateRequest(HttpMethod.GET, new HttpEntity<>(createExpectedHeaders()));
	}

	@Test
	public void testRetry_whenUsingDefaultRetry_thenThereMustBeThreeAttempts(){
		doThrow(new RuntimeException())
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		assertThatThrownBy(() -> createBasicRequest().get(String.class))
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(3)).exchange(any(URI.class), eq(HttpMethod.GET), eq(new HttpEntity<>(createExpectedHeaders())), eq(String.class));
	}

	@Test
	public void testRetry_whenUsingDefinedRetry_thenThereMustBeTheCorrectNumberOfAttempts(){
		doThrow(new RuntimeException())
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		assertThatThrownBy(() -> createBasicRequest().withRetry(RetryBuilder.exponential().maxAttempts(4).build()).get(String.class))
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(4)).exchange(any(URI.class), eq(HttpMethod.GET), eq(new HttpEntity<>(createExpectedHeaders())), eq(String.class));
	}

	@Test
	public void testRetry_whenUsingDefinedRetryWithStatusesToNotRetry_thenNoRetriesArePresentWhenSpecifiedStatusIsReturned(){
		final RuntimeException toBeThrown = new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		doThrow(toBeThrown)
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		assertThatThrownBy(() -> createBasicRequest()
				.withRetry(
						RetryBuilder.exponential().maxAttempts(4).build(),
						HttpStatus.UNAUTHORIZED)
				.get(String.class))
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(1)).exchange(any(URI.class), eq(HttpMethod.GET), eq(new HttpEntity<>(createExpectedHeaders())), eq(String.class));
	}

	@Test
	public void testRetry_whenSpecifyingNoRetryOnGet_thenThereMustBeOnlyOneAttempt(){
		doThrow(new RuntimeException())
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		assertThatThrownBy(() -> createBasicRequest().noRetry().get(String.class))
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(1)).exchange(any(URI.class), eq(HttpMethod.GET), eq(new HttpEntity<>(createExpectedHeaders())), eq(String.class));
	}

	@Test
	public void testRetry_whenSpecifyingNoRetryOnDelete_thenThereMustBeOnlyOneAttempt(){
		doThrow(new RuntimeException())
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));

		assertThatThrownBy(() -> createBasicRequest().noRetry().delete())
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(1)).exchange(any(URI.class), eq(HttpMethod.DELETE), eq(new HttpEntity<>(createExpectedHeaders())), eq(String.class));
	}

	@Test
	public void testRetry_whenSpecifyingNoRetryWithParameterizedTypeReference_thenThereMustBeOnlyOneAttempt(){
		doThrow(new RuntimeException())
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(new ParameterizedTypeReference<List<String>>(){}));

		assertThatThrownBy(() -> createBasicRequest().noRetry().get(new ParameterizedTypeReference<List<String>>(){}))
				.isInstanceOf(RuntimeException.class);

		verify(restOperations, times(1)).exchange(any(URI.class), eq(HttpMethod.GET), eq(new HttpEntity<>(createExpectedHeaders())), eq(new ParameterizedTypeReference<List<String>>(){}));
	}

	@Test
	public void testMediaType(){
		doReturn(ResponseEntity.ok("Hello"))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));
		createRestResource().request(MediaType.APPLICATION_XML)
				.get(String.class);

		final HttpEntity entity = httpEntityArgumentCaptor.getValue();
		assertThat(entity.getHeaders().get(HttpHeaders.CONTENT_TYPE)).containsExactly(MediaType.APPLICATION_XML.toString());
	}

	@Test
	public void testPath(){
		doReturn(ResponseEntity.ok("Hello"))
				.when(restOperations)
				.exchange(uriArgumentCaptor.capture(),
						httpMethodArgumentCaptor.capture(),
						httpEntityArgumentCaptor.capture(),
						eq(String.class));
		createRestResource().request()
				.path("hello/search")
				.get(String.class);

		final URI uri = uriArgumentCaptor.getValue();
		SoftAssertions.assertSoftly(softly ->{
			softly.assertThat(uri.getPath()).describedAs("PATH").isEqualTo("/hello/search");
			softly.assertThat(uri.toString()).describedAs("FULL URI").isEqualTo("http://localhost:8080/hello/search");
		});
	}

	private void validateRequest(HttpMethod expectedMethod, HttpEntity expectedEntity) {
		final URI uri = uriArgumentCaptor.getValue();
		final HttpMethod method = httpMethodArgumentCaptor.getValue();
		final HttpEntity entity = httpEntityArgumentCaptor.getValue();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(uri.getHost()).describedAs("HOST").isEqualTo("localhost");
			softly.assertThat(uri.getPort()).describedAs("PORT").isEqualTo(8080);
			softly.assertThat(uri.getPath()).describedAs("PATH").isEqualTo("/hello");
			softly.assertThat(uri.getAuthority()).describedAs("AUTHORITY").isEqualTo("localhost:8080");
			softly.assertThat(uri.getQuery()).describedAs("QUERY").isEqualTo("oh=my&this=isCool");
			softly.assertThat(uri.getScheme()).describedAs("SCHEMA").isEqualTo("http");
			softly.assertThat(uri.toString()).describedAs("FULL URI").isEqualTo("http://localhost:8080/hello?oh=my&this=isCool");
			softly.assertThat(method).isEqualByComparingTo(expectedMethod);
			softly.assertThat(entity).isEqualToComparingFieldByFieldRecursively(expectedEntity);
		});
	}

	private HttpRequestBuilder createBasicRequest(){
		return createRestResource().request(REQUEST_ID, CORRELATION_ID)
				.pathSegment("hello")
				.queryParam("oh", "my")
				.queryParam("this", "isCool");
	}

	private RestResource createRestResource() {
		return new RestClient(restOperations, URI.create("http://localhost:8080"));
	}

	private HttpHeaders createExpectedHeaders(){
		PartnerCenterHttpHeaders headers = new PartnerCenterHttpHeaders();
		headers.setMsRequestId(REQUEST_ID);
		headers.setMsCorrelationId(CORRELATION_ID);
		return headers;
	}
}
