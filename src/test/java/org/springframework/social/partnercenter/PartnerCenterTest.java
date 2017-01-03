package org.springframework.social.partnercenter;

import org.junit.jupiter.api.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.invoicing.Invoice;
import org.springframework.social.partnercenter.api.billing.pricing.AzureResourcePricing;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;
import org.springframework.social.partnercenter.connect.PartnerCenterConnectionFactory;

public class PartnerCenterTest {

	@Test
	public void someTest(){
		PartnerCenterConnectionFactory factory = new PartnerCenterConnectionFactory("9193d755-e3fe-4510-a954-e1f5046fbab0",
				"DTW10c2hW8hllL5vqV8Pd2omzBKhWMmmaNQP1rcgiuw=",
				"testtestappdirecttip.onmicrosoft.com");
		PartnerCenter partnerCenter = factory.createConnection().getApi();
		CustomerOperations customerOperations = partnerCenter.getCustomerOperations();
		PartnerCenterResponse<Customer> list = customerOperations.getList(100);
		Customer customer = list.getItems().get(1);
		PartnerCenterResponse<Invoice> invoices = partnerCenter.getInvoiceOperations().getInvoices();
		AzureResourcePricing azurePricing = partnerCenter.getPricingOperations().getAzurePricing();
//		CustomerUsageSummary usageSummary = partnerCenter.getUsageOperations().getUsageSummary(customer.getId());
		GetSubscriptionListResponse customersSubscriptions = partnerCenter.getSubscriptionOperations().getCustomersSubscriptions(customer.getId());
	}
}
