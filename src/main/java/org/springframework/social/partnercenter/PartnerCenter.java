package org.springframework.social.partnercenter;

import java.util.Locale;

import org.springframework.social.ApiBinding;
import org.springframework.social.partnercenter.api.agreement.AgreementOperations;
import org.springframework.social.partnercenter.api.analytics.AnalyticsOperations;
import org.springframework.social.partnercenter.api.audit.AuditOperations;
import org.springframework.social.partnercenter.api.billing.invoicing.InvoiceOperations;
import org.springframework.social.partnercenter.api.billing.pricing.PricingOperations;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.consent.ConsentOperations;
import org.springframework.social.partnercenter.api.customer.CustomerOperations;
import org.springframework.social.partnercenter.api.customer.user.UserOperations;
import org.springframework.social.partnercenter.api.customer.user.role.DirectoryRoleOperations;
import org.springframework.social.partnercenter.api.customer.user.role.RoleOperations;
import org.springframework.social.partnercenter.api.order.OrderOperations;
import org.springframework.social.partnercenter.api.order.offer.OfferOperations;
import org.springframework.social.partnercenter.api.order.subscription.SubscriptionOperations;
import org.springframework.social.partnercenter.api.profile.ProfileOperations;
import org.springframework.social.partnercenter.api.relationships.RelationshipOperations;
import org.springframework.social.partnercenter.api.support.SupportOperations;
import org.springframework.social.partnercenter.api.utilities.UtilityOperations;
import org.springframework.social.partnercenter.http.logging.LogLevel;

public interface PartnerCenter extends ApiBinding {
	String PROVIDER_ID = "partner-center";
	SubscriptionOperations getSubscriptionOperations();
	OrderOperations getOrderOperations();
	CustomerOperations getCustomerOperations();
	OfferOperations getOfferOperations();
	UsageOperations getUsageOperations();
	PricingOperations getPricingOperations();
	InvoiceOperations getInvoiceOperations();
	ProfileOperations getProfileOperations();
	UtilityOperations getUtilityOperations();
	UserOperations getUserOperations();
	AuditOperations getAuditOperations();
	RelationshipOperations getRelationshipOperations();
	AnalyticsOperations getAnalyticsOperations();
	SupportOperations getSupportOperations();
	DirectoryRoleOperations getDirectoryRoleOperations();
	RoleOperations getRoleOperations();
	ConsentOperations getConsentOperations();
	AgreementOperations getAgreementOperations();
	void enableSlf4j(LogLevel level);
	void setLocale(Locale locale);
	boolean isSlf4jEnabled();
	String getDomain();
}
