package org.springframework.social.partnercenter.api.order.subscription;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.patchRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.social.partnercenter.test.stubs.StubURI.baseURI;
import static org.springframework.social.partnercenter.test.stubs.SubscriptionOperationStubs.given_getById_200_OK;
import static org.springframework.social.partnercenter.test.stubs.SubscriptionOperationStubs.given_patch_200_OK;
import static org.springframework.social.partnercenter.test.stubs.TestRestTemplateFactory.createRestTemplate;

import java.time.format.DateTimeFormatter;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.social.partnercenter.api.order.subscription.impl.SubscriptionTemplate;
import org.springframework.social.partnercenter.http.client.RestClient;
import org.springframework.social.partnercenter.test.Resource;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class SubscriptionOperationsTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().dynamicPort());

	@Test
	public void testById_whenCalled_thenResponseIsParsedCorrectly() {
		given_getById_200_OK();

		final SubscriptionOperations subscriptionOperations = new SubscriptionTemplate(
				new RestClient(createRestTemplate(), baseURI(wireMockRule.port(), "v1", "customers")),
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

	@Test
	public void testUpdateSubscription_validateRequestSerializedCorrectly() {
		given_patch_200_OK();

		final SubscriptionOperations subscriptionOperations = new SubscriptionTemplate(
				new RestClient(createRestTemplate(), baseURI(wireMockRule.port(), "v1", "customers")),
				true);

		final Subscription subscription = Resource.parseFile("data/subscription/ok.json").getJsonAsObject(Subscription.class);
		subscriptionOperations.updateSubscription("hello", "world",
				subscription);

		verify(patchRequestedFor(urlPathEqualTo("/v1/customers/hello/subscriptions/world"))
		.withRequestBody(equalToJson(Resource.parseFile("data/subscription/ok.json").getAsString(), true, true)));
	}

}
