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

public class ApiHttpBodyLogFormatterTest {

	@Test
	public void testFormatRequestLog_whenFormattingResquestBody_correctBodyIsLogged() {
		String d = "{\"key\":\"value\",\"hello\":\"world\",\"test\":\"one, two\"}";
		final String s = new ApiHttpBodyLogFormatter().formatRequestLogs(d.getBytes());
		assertThat(s).isEqualTo("Body:\n\t"+d);
	}

	@Test
	public void testFormatResponseLogs_whenFormattingResponseBody_correctBodyIsLogged() throws IOException {
		String responseBody = "{\"key\":\"value\",\"hello\":\"world\",\"test\":\"one, two\"}";
		InputStream responseBodyStream = new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8.name()));
		final ClientHttpResponse httpResponse = spy(ClientHttpResponse.class);
		doReturn(responseBodyStream).when(httpResponse).getBody();
		final String result = new ApiHttpBodyLogFormatter().formatResponseLogs(httpResponse);
		assertThat(result).isEqualTo("Body:\n\t"+ responseBody);
	}

	@Test
	public void testFormatResponseLogs_whenExceptionThrownWhileParsing_thenBodyIsNotWriten() throws IOException {
		final ClientHttpResponse httpResponse = spy(ClientHttpResponse.class);
		doThrow(new IOException()).when(httpResponse).getBody();
		final String result = new ApiHttpBodyLogFormatter().formatResponseLogs(httpResponse);
		assertThat(result).isEqualTo("Body:\n\t"+ "Could not read response body for logging");
	}
}
