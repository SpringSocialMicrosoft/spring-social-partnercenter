## 5.0.1
This release changes several date fields to java.date types instead of _string_ and fixes bugs in UserOperations and PricingOperations

#### Changes
1. Changed **org.springframework.social.partnercenter.api.billing.pricing.AzureResourcePricing.locale** to **java.util.Locale** instead of **String**.
1. Changed **org.springframework.social.partnercenter.api.billing.pricing.OfferTerm.effectiveDate** to **java.time.Instant** instead of **String**.
1. Changed all of the following to **java.time.ZonedDateTime** instead of **String**.
	* org.springframework.social.partnercenter.api.billing.pricing.PricingMeter.effectiveDate
	* org.springframework.social.partnercenter.api.order.subscription.Subscription.creationDate
	* org.springframework.social.partnercenter.api.order.subscription.Subscription.effectiveStartDate
	* org.springframework.social.partnercenter.api.order.subscription.Subscription.commitmentEndDate
1. Fixed bug passing Locale to **org.springframework.social.partnercenter.api.billing.pricing.PricingOperations. getAzurePricing**
1. Fixed bug in **org.springframework.social.partnercenter.api.customer.user.AdminUserOperations.assignLicensesToUser**
1. Changed parameter object for  **AdminUserOperations.assignLicensesToUser**. Previous contract was incorrect and lead to the aforementioned bug.

## 5.0.2

Bug Fixes
1. Fixed serialization issue with date attributs in `org.springframework.social.partnercenter.api.order.subscription.Subscription` class.
