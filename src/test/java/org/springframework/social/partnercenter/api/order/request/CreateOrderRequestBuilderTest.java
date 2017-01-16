package org.springframework.social.partnercenter.api.order.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.springframework.social.partnercenter.api.order.request.CreateOrderRequest.createOrderRequestBuilder;
import static org.springframework.social.partnercenter.api.order.request.CreateOrderRequestLineItem.lineItemBuilder;

import org.assertj.core.util.Maps;
import org.junit.Test;

public class CreateOrderRequestBuilderTest {

	private static final String CUSTOMER_ID = "customerID";
	private static final String FIRST_FRIENDLY_NAME = "My Friendly Line Item";
	private static final String SECOND_FRIENDLY_NAME = "Yo ho ho and a bottle of rum";
	private static final String FIRST_OFFER_ID = "hfjdsfadshajlkfhdsa";
	private static final String SECOND_OFFER_ID = "fhdhsjafkdsad";
	private static final String KEY = "Kind";
	private static final String VALUE = "Subscription";

	@Test
	public void build_whenBuilderIsUsed_theCorrectLineItemsAreCreated(){
		CreateOrderRequest createOrderRequest = createOrderRequestBuilder().referenceCustomerId(CUSTOMER_ID)
				.addLineItem(lineItemBuilder()
						.friendlyName(FIRST_FRIENDLY_NAME)
						.offerId(FIRST_OFFER_ID)
						.quantity(16).build())
				.addLineItem(lineItemBuilder()
						.quantity(12)
						.offerId(SECOND_OFFER_ID)
						.friendlyName(SECOND_FRIENDLY_NAME).build())
				.attributes(Maps.newHashMap(KEY, VALUE))
				.build();

		assertThat(createOrderRequest.getLineItems())
				.extracting("offerId")
				.containsExactly(FIRST_OFFER_ID, SECOND_OFFER_ID);
		assertThat(createOrderRequest.getLineItems())
				.extracting("friendlyName")
				.containsExactly(FIRST_FRIENDLY_NAME, SECOND_FRIENDLY_NAME);
		assertThat(createOrderRequest.getLineItems())
				.extracting("lineItemNumber")
				.containsExactly(0, 1);
		assertThat(createOrderRequest.getLineItems())
				.extracting("quantity")
				.containsExactly(16, 12);
		assertThat(createOrderRequest.getAttributes()).containsExactly(entry(KEY, VALUE));
		assertThat(createOrderRequest.getReferenceCustomerId()).isEqualTo(CUSTOMER_ID);
	}

}
