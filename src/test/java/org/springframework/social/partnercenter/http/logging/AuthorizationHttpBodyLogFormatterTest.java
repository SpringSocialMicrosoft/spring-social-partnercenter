package org.springframework.social.partnercenter.http.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.springframework.http.client.ClientHttpResponse;

import net.minidev.json.JSONObject;

public class AuthorizationHttpBodyLogFormatterTest {

	@Test
	public void testFormatRequestLogs_whenFormattingRequestBody_theCorrectFieldsAreObfuscated() {
		String requestBody = "grant_type=password&username=paul.smelser%40gmail.com&password=password1234&client_id=asdfjklfgdhsjakfdsa&client_secret=bhfdksafdhsafdsa=";
		String expectedBody = requestBody
				.replace("password1234", "*")
				.replace("bhfdksafdhsafdsa=", "*");
		final String result = new AuthorizationHttpBodyLogFormatter().formatRequestLogs(requestBody.getBytes());
		assertThat(result).isEqualTo("Body:\n\t"+ expectedBody);
	}

	@Test
	public void testFormatRequestLogs_whenFormattingRequestBodyWithRefreshToken_theCorrectFieldsAreObfuscated() {
		final String refreshToken = "hfjkdlshafhdsavghfbdsjfklhdsajfkldsabfbdsajhklfdsa";
		String requestBody = "grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=asdfjklfgdhsjakfdsa&scope=openid";
		String expectedBody = requestBody
				.replace("hfjkdlshafhdsavghfbdsjfklhdsajfkldsabfbdsajhklfdsa", "*");
		final String result = new AuthorizationHttpBodyLogFormatter().formatRequestLogs(requestBody.getBytes());
		assertThat(result).isEqualTo("Body:\n\t"+ expectedBody);
	}

	@Test
	public void testFormatResponseLogs_whenFormattingResponseBody_theCorrectFieldsAreObfuscated() throws IOException {
		String responseBody = buildAuthResponse();
		InputStream responseBodyStream = new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8.name()));
		final ClientHttpResponse httpResponse = spy(ClientHttpResponse.class);
		doReturn(responseBodyStream).when(httpResponse).getBody();
		final String result = new AuthorizationHttpBodyLogFormatter().formatResponseLogs(httpResponse);
		assertThat(result).isEqualTo("Body:\n\t"+ buildExpectedAuthResponseLog());
	}
	@Test
	public void testFormatResponseLogs_whenFormattingResponseBodyWithoutIdToken_thenTheIdTokenIsNotIncludedInTheResponse() throws IOException {
		String responseBody = buildAuthResponseWithoutIdToken();
		InputStream responseBodyStream = new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8.name()));
		final ClientHttpResponse httpResponse = spy(ClientHttpResponse.class);
		doReturn(responseBodyStream).when(httpResponse).getBody();
		final String result = new AuthorizationHttpBodyLogFormatter().formatResponseLogs(httpResponse);
		assertThat(result).isEqualTo("Body:\n\t"+ buildExpectedAuthResponseLogIfThereIsNoIdToken());
	}

	@Test
	public void testFormatResponseLogs_whenAnExceptionIsThrown_theBodyIsNotWritten() throws IOException {
		final ClientHttpResponse httpResponse = spy(ClientHttpResponse.class);
		doThrow(new IOException()).when(httpResponse).getBody();
		final String result = new AuthorizationHttpBodyLogFormatter().formatResponseLogs(httpResponse);
		assertThat(result).isEqualTo("Body:\n\t"+ "Could not read response body for logging");
	}

	private String buildAuthResponse(){
		JSONObject json = new JSONObject();
		json.put("token_type", "Bearer");
		json.put("access_token", "jfdsfjdksla;fjdksal;fdsuagfhdjsanlfjdsa");
		json.put("refresh_token", "fhdjksafhdsaiufydbsahfdsafdsafdsahtrhrte");
		json.put("id_token", "fdnsjkfbdsauflndjsbayvugdsavdsanjfldsafdsa");
		return json.toJSONString();
	}

	private String buildAuthResponseWithoutIdToken(){
		JSONObject json = new JSONObject();
		json.put("token_type", "Bearer");
		json.put("access_token", "jfdsfjdksla;fjdksal;fdsuagfhdjsanlfjdsa");
		json.put("refresh_token", "fhdjksafhdsaiufydbsahfdsafdsafdsahtrhrte");
		return json.toJSONString();
	}

	private String buildExpectedAuthResponseLog(){
		JSONObject json = new JSONObject();
		json.put("token_type", "Bearer");
		json.put("access_token", "*");
		json.put("refresh_token", "*");
		json.put("id_token", "*");
		return json.toJSONString();
	}

	private String buildExpectedAuthResponseLogIfThereIsNoIdToken(){
		JSONObject json = new JSONObject();
		json.put("token_type", "Bearer");
		json.put("access_token", "*");
		json.put("refresh_token", "*");
		return json.toJSONString();
	}
}
