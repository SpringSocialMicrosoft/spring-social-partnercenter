package org.springframework.social.partnercenter.security;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.social.partnercenter.api.ApiAuthorizationException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;

public class AzureADAuthTemplateTest {
	@Mock
	private RestTemplate restTemplate;
	private final String RESPONSE_BODY = "{\"error\":\"invalid_resource\",\"error_description\":\"AADSTS50001: The application named https://management.azure.com/ was not found in the tenant named alextest030617.onmicrosoft.de.  This can happen if the application has not been installed by the administrator of the tenant or consented to by any user in the tenant.  You might have sent your authentication request to the wrong tenant.\\r\\nTrace ID: b262bde5-777b-491c-8591-ed1b72389c20\\r\\nCorrelation ID: 607a9e54-c610-4893-a82f-7bea7a8e03db\\r\\nTimestamp: 2017-03-07 04:15:39Z\",\"error_codes\":[50001],\"timestamp\":\"2017-03-07 04:15:39Z\",\"trace_id\":\"b262bde5-777b-491c-8591-ed1b72389c20\",\"correlation_id\":\"607a9e54-c610-4893-a82f-7bea7a8e03db\"}";

	private AzureADAuthTemplate azureADAuthTemplate;

	@Before
	public void init(){
		initMocks(this);
		azureADAuthTemplate = new AzureADAuthTemplate("appId", "appSecret", "clientId", "domain", restTemplate);
		doThrow(new HttpClientErrorException(BAD_REQUEST, BAD_REQUEST.getReasonPhrase(), RESPONSE_BODY.getBytes(Charsets.UTF_8), Charsets.UTF_8))
		.when(restTemplate).postForObject(anyString(), any(MultiValueMap.class), any());
	}

	@Test
	public void test(){
		assertThatThrownBy(() -> azureADAuthTemplate.exchangeForAccess())
				.isExactlyInstanceOf(ApiAuthorizationException.class)
				.hasMessage("AADSTS50001: The application named https://management.azure.com/ was not found in the tenant named alextest030617.onmicrosoft.de.  This can happen if the application has not been installed by the administrator of the tenant or consented to by any user in the tenant.  You might have sent your authentication request to the wrong tenant.\r\nTrace ID: b262bde5-777b-491c-8591-ed1b72389c20\r\nCorrelation ID: 607a9e54-c610-4893-a82f-7bea7a8e03db\r\nTimestamp: 2017-03-07 04:15:39Z")
				.matches(o -> ((ApiAuthorizationException)o).getError().equals("invalid_resource"))
				.matches(o -> ((ApiAuthorizationException)o).getCorrelation_id().equals("607a9e54-c610-4893-a82f-7bea7a8e03db"))
				.matches(o -> ((ApiAuthorizationException)o).getErrorCodes().contains(50001));
	}
}
