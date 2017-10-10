package org.springframework.social.partnercenter.api.order.subscription;

import static org.springframework.social.partnercenter.test.stubs.SubscriptionOperationStubs.given_getById_200_OK;

import java.net.URI;
import java.time.format.DateTimeFormatter;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.order.subscription.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class SubscriptionOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	@Test
	public void testById_whenCalled_thenResponseIsParsedCorrectly() {
		given_getById_200_OK();

		final SubscriptionOperations subscriptionOperations = new SubscriptionTemplate(
				new RestClient(TestRestTemplateFactory.createRestTemplate(),URI.create("http://localhost:8080/v1/customers")),
				true);

		final Subscription subscription = subscriptionOperations.getById(
				"cec7381b-58d1-455d-b516-134caf447ffa",
				"6C36999D-F573-408D-A463-ED9EAC6319E1").getBody();

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(subscription.getCommitmentEndDate())).isEqualTo("9999-12-04T00:00:00Z");
			softly.assertThat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(subscription.getEffectiveStartDate())).isEqualTo("2017-10-04T00:00:00Z");
			softly.assertThat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(subscription.getCreationDate())).isEqualTo("2017-10-04T09:40:28.773Z");
		});
	}

}
