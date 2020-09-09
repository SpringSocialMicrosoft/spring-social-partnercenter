# Release Notes: Spring Social for Microsoft Partner Center

## 10.6.0
#### Added
- Added ability to retrieve refresh token from PartnerCenterConnection
  class

## 10.5.0
#### Added
- Added some logs to AzureADMultiTenantOAuthTemplate

## 10.4.0
#### Added
- Added new API to Customer Operations

## 10.3.0
#### Changed
- Changed queryParam to seek_operation

## 10.2.0
#### Changed
- Changed next method to used operationSeek NEXT

## 10.1.0
#### Changed
- Change definition of new next method `ResponseEntity<PartnerCenterResponse<UtilizationRecord>> next(String continuationToken, String customerId, String subscriptionId)`

## 10.0.0
#### Added
- Added new next method `ResponseEntity<PartnerCenterResponse<UtilizationRecord>> next(String continuationToken, String customerId, String subscriptionId)`

## 9.4.0
#### Added
- Added missing `upgradeTargetOffers` attribute to `Offer` bean
#### Changed
- Change explicit `getters`, `setters` and `builder` by Lombok annotations.

## 9.0.2
#### Added
- Added MicrosoftCustomerAgreement, and the templateId of each agreement to AgreementType

## 9.0.1
#### Added
- Added DAP (Delegated Admin Privileges) `allowDelegatedAccess` and `associatedPartnerId` fields to Customer

## 9.0.0
#### Changed
- Change data type for rates in the `PricingMeter` class to use `BigDecimal` instead of `double` to avoid loss of precision with large number of decimals

## 8.0.2
#### Changed
- Change `dateAgreed` type from `Instant` to `String` due to API inconsistencies

## 8.0.1
#### Fixed
- `buildAuthorizeUrl` on `UriProvider` used a constant authority instead of taking the authority from the `UriProvider`
 instance

## 8.0.0
#### Changed
1. SecurityRegion is no longer an enum. It is now a class so that anyone can create their own custom SecurityRegion if necessary
2. Move out AgreementMetaData operations of AgreementOperations, now in AgreementMetaDataOperations
3. PartnerCenterMultiTenantConnectionFactory now requires a `SecurityRegion` to be instantiated and the `createConnection` method doesn't

## 7.5.0
#### Added
1. [Agreement api](https://docs.microsoft.com/en-us/partner-center/develop/get-agreement-metadata) to give customer consent on behalf of the reseller

## 7.4.0
#### Added
1. Consent api to give customer consent to certain APIs as a delegated customer access
2. Pagination method for usersOperations

## 7.3.0
#### Added
1. New Multi tenant connection factory that implements new authentication method to be used during an interactive login with Microsoft.

## 7.2.2
#### Bug Fixes
1. Fixed bug in authentication flow for user + app. Only present in version 7.2.1

## 7.2.1
#### Warning
1. This change has a bug in auth flows. Please use 7.2.2

## 7.2.0
#### Added
1. Added new Connection factories to separate App and User + App authentications. 

#### Changed
1. Added currencySymbol, amendments, documentType, amendsOf and invoiceType to Invoice DTO 

## 7.1.1
#### Added
1. Added null checks for method parameters. (All Operations)

## 7.1.0
#### Added
1. Added Partner Relationships Operations

#### Removed
1. Removed deprecated methods from UtilityOperations
2. Removed deprecation warnings from AuditOperations that were marked as deprecated erroneously

## 7.0.0
#### Added
1. Added DirectoryRole and Role operations.
2. Added new Usage operations for getting usage totals

#### Improvements
1. Updated UtilizationRecord to use ZonedDateTime instead of string for DateTime fields.

## 6.2.3

#### Bug Fixes
1. Fixed failure in PartnerCenterAdminConnection constructor `PartnerCenterAdminConnection(ConnectionData, ApiAdaptor, ServiceProvider)`.

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

## 5.1.1 (Merge back fixes in 6.2.2) 

#### Bug Fixes
1. Fixed Conversion Result message contract. The properties from ConversionError and ConversionResult were swapped

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
