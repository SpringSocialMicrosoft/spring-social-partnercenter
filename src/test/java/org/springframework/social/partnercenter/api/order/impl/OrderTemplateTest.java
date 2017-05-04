package org.springframework.social.partnercenter.api.order.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionProvisioningState;
import org.springframework.social.partnercenter.http.client.RestResource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderTemplateTest {
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private RestResource restResource;

	private OrderTemplate orderTemplate;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		orderTemplate = new OrderTemplate(restResource, true);
	}

	@Test
	public void getSubscriptionProvisioningState() throws Exception {
		when(restResource.request()
				.pathSegment(eq("customerTenantId"), eq("subscriptions"), eq("subscriptionId"), eq("provisioningstatus"))
				.get(eq(SubscriptionProvisioningState.class))).thenReturn(new ResponseEntity(createSubscriptionProvisioningStateJsonPayload(), HttpStatus.OK));

		SubscriptionProvisioningState subscriptionProvisioningState = orderTemplate.getSubscriptionProvisioningState("customerTenantId", "subscriptionId").getBody();

		assertEquals(subscriptionProvisioningState.getSkuId(), "CDD28E44-67E3-425E-BE4C-737FAB2899D3");
		assertEquals(subscriptionProvisioningState.getStatus(), "success");
		assertEquals(subscriptionProvisioningState.getQuantity(), 5);
		assertEquals(subscriptionProvisioningState.getEndDate(), "2018-05-10T00:00:00Z");
		assertEquals(subscriptionProvisioningState.getAttributes().size(), 1);
		assertTrue(subscriptionProvisioningState.getAttributes().containsKey("objectType"));
		assertEquals(subscriptionProvisioningState.getAttributes().get("objectType"), "SubscriptionProvisioningStatus");
	}

	private SubscriptionProvisioningState createSubscriptionProvisioningStateJsonPayload() throws IOException {
		String jsonResponse = IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("org/springframework/social/partnercenter/api/order/impl/SubscriptionProvisioningState.json"));
		return new ObjectMapper().readValue(jsonResponse, SubscriptionProvisioningState.class);
	}

}
