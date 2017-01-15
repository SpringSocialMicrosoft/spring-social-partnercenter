package org.springframework.social.partnercenter.api.order.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.social.partnercenter.api.order.request.CreateOrderRequestLineItem.lineItemBuilder;
import static org.springframework.social.partnercenter.api.order.request.CreateOrderRequestLineItem.lineItemListBuilder;

import java.util.List;

import org.junit.Test;

public class LineItemListBuilderTest {

	@Test
	public void build_whenLineItemListBuilderIdUsed_theCorrectLineItemsAreCreated() {
		List<CreateOrderRequestLineItem> lineItemList = lineItemListBuilder()
				.addLineItem(lineItemBuilder()
						.friendlyName("My Friendly Line Item")
						.offerId("hfjdsfadshajlkfhdsa")
						.quantity(16))
				.addLineItem(lineItemBuilder()
						.quantity(12)
						.offerId("fhdhsjafkdsad")
						.friendlyName("Yo ho ho and a bottle of rum")
				).build();

		assertThat(lineItemList)
				.extracting("offerId")
				.containsExactly("hfjdsfadshajlkfhdsa", "fhdhsjafkdsad");
		assertThat(lineItemList)
				.extracting("friendlyName")
				.containsExactly("My Friendly Line Item", "Yo ho ho and a bottle of rum");
		assertThat(lineItemList)
				.extracting("lineItemNumber")
				.containsExactly(0, 1);
		assertThat(lineItemList)
				.extracting("quantity")
				.containsExactly(16, 12);

	}
}
