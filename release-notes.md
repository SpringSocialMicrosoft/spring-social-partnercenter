# Release Notes: Spring Social for Microsoft Partner Center

## 6.2.2

#### Bug Fixes
1. Fixed Conversion Result message contract. The properties from ConversionError and ConversionResult were swapped

## 6.2.1

#### Bug Fixes
1. Fixed AuthorizationTemplate to ensure failed rest calls get wrapped as ApiAuthorizationException.

## 6.2.0

#### Added
1. ServiceRequestOperations added to allow for creating and retrieving service requests through the API

## 6.1.0

#### Bug Fixes
1. Fixed logging issue where multiple redundant loggers were added when logging was enabled through `enableSlf4j(LogLevel level)` 
on `org.springframework.social.partnercenter.api.PartnerCenterTemplate` and `org.springframework.social.partnercenter.security.AzureADAuthTemplate`

## 5.1.0
#### Bug Fixes
1. Fixed logging issue where multiple redundant loggers were added when logging was enabled through `enableSlf4j(LogLevel level)` 
on `org.springframework.social.partnercenter.api.PartnerCenterTemplate` and `org.springframework.social.partnercenter.security.AzureADAuthTemplate`

## 4.0.17

#### Bug Fixes
1. Fixed logging issue where multiple redundant loggers were added when logging was enabled through `enableSlf4j(LogLevel level)` 
on `org.springframework.social.partnercenter.api.PartnerCenterTemplate` and `org.springframework.social.partnercenter.security.AzureADAuthTemplate`

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

## 5.0.3 (Merge back fixes in 6.2.2) 

#### Bug Fixes
1. Fixed Conversion Result message contract. The properties from ConversionError and ConversionResult were swapped

## 5.0.2

#### Bug Fixes
1. Fixed serialization issue with date attributes in `org.springframework.social.partnercenter.api.order.subscription.Subscription` class.


## 6.0.0

#### Updates
1. Modified authorization methods in connection to use the refresh token when refreshing connection.
1. Added Suspension Reasons to `org.springframework.social.partnercenter.api.order.subscription.Subscription`

#### Bug Fixes
1. Updated `org.springframework.social.partnercenter.api.customer.user.License` to match API documentation. 
