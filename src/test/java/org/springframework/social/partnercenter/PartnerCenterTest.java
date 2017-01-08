package org.springframework.social.partnercenter;

import org.junit.jupiter.api.Test;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.invoicing.Invoice;
import org.springframework.social.partnercenter.api.customer.Customer;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.response.GetCompanyProfileResponse;
import org.springframework.social.partnercenter.api.customer.response.GetSubscriptionListResponse;
import org.springframework.social.partnercenter.api.order.Order;
import org.springframework.social.partnercenter.api.utilities.CountryInformation;
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
		PartnerCenterResponse<Order> aCustomersOrder = partnerCenter.getOrderOperations().getACustomersOrder(list.getItems().get(1).getId());
		GetCompanyProfileResponse customersCompanyProfile = partnerCenter.getCustomerOperations().getCustomersCompanyProfile(list.getItems().get(1).getId());
		Customer customer = list.getItems().get(1);
		PartnerCenterResponse<Invoice> invoices = partnerCenter.getInvoiceOperations().getInvoices();
		Boolean domainAvailable = partnerCenter.getUtilityOperations().isDomainAvailable("oozieconsole");
		CountryInformation us = partnerCenter.getUtilityOperations().getAddressFormattingRulesByMarket("us");
//		CustomerUsageSummary usageSummary = partnerCenter.getUsageOperations().getUsageSummary(customer.getId());

		GetSubscriptionListResponse customersSubscriptions = partnerCenter.getSubscriptionOperations().getCustomersSubscriptions(customer.getId());
	}
}
