package org.springframework.social.partnercenter.connect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.connect.support.MultiTenantConnectionFactoryRegistry;

public class MultiTenantConnectionFactoryRegistryTest {

	private static final String APPDIRECT_COM = "appdirect.com";
	private static final String GOOGLE_COM = "google.com";
	private static final String APPLE_COM = "apple.com";
	private static final String MICROSOFT_COM = "microsoft.com";

	@Test
	public void getConnectionFactory_whenCalled_returnsTheCorrectConnectionFactory(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		registry.addConnectionFactory(GOOGLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(APPLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(MICROSOFT_COM, createTestConnectionFactory());
		ConnectionFactory<PartnerCenter> factory = registry.getConnectionFactory(APPDIRECT_COM, PartnerCenter.class);
		assertThat(factory).isEqualTo(registry.getConnectionFactory(APPDIRECT_COM, factory.getProviderId()));
	}

	@Test
	public void registeredProviderIds_whenCalled_returnsTheCorrectTenants(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		registry.addConnectionFactory(GOOGLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(APPLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(MICROSOFT_COM, createTestConnectionFactory());
		assertThat(registry.registeredTenantIds()).containsOnly(APPDIRECT_COM, GOOGLE_COM, APPLE_COM, MICROSOFT_COM);
	}

	@Test
	public void registeredProviderIds_whenCalled_returnsTheCorrectProviders(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		registry.addConnectionFactory(GOOGLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(APPLE_COM, createTestConnectionFactory());
		registry.addConnectionFactory(MICROSOFT_COM, createTestConnectionFactory());
		assertThat(registry.registeredProviderIds()).containsOnly(PartnerCenter.PROVIDER_ID, PartnerCenter.PROVIDER_ID, PartnerCenter.PROVIDER_ID, PartnerCenter.PROVIDER_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addConnectionFactory_whenProviderAndTenantIdAlreadyRegistered_thenThrowsIllegalArgumentException(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
	}

	@Test
	public void isRegistered_whenConnectionFactoryIsRegistered_thenReturnTrue(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		assertThat(registry.isRegistered(APPDIRECT_COM, PartnerCenter.PROVIDER_ID)).isTrue();
		assertThat(registry.isRegistered(APPDIRECT_COM, PartnerCenter.class)).isTrue();
	}

	@Test
	public void isRegistered_whenTenantIdIsNotRegistered_thenReturnFalse(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		assertThat(registry.isRegistered(GOOGLE_COM, PartnerCenter.PROVIDER_ID)).isFalse();
		assertThat(registry.isRegistered(GOOGLE_COM, PartnerCenter.class)).isFalse();
	}

	@Test
	public void getConnectionFactory_whenTenantIdIsNotRegistered_thenThrowIllegalArgumentException(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		assertThatThrownBy(() -> registry.getConnectionFactory(GOOGLE_COM, PartnerCenter.PROVIDER_ID))
				.isExactlyInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> registry.getConnectionFactory(GOOGLE_COM, PartnerCenter.class))
				.isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void isRegistered_whenApiTypeIsNotRegistered_thenReturnFalse(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		assertThat(registry.isRegistered(APPDIRECT_COM, "hello")).isFalse();
		assertThat(registry.isRegistered(APPDIRECT_COM, getClass())).isFalse();
	}

	@Test
	public void getConnectionFactory_whenApiTypeIsNotRegistered_thenThrowIllegalArgumentException(){
		MultiTenantConnectionFactoryRegistry registry = new MultiTenantConnectionFactoryRegistry();
		registry.addConnectionFactory(APPDIRECT_COM, createTestConnectionFactory());
		assertThatThrownBy(() -> registry.getConnectionFactory(APPDIRECT_COM, "hello"))
				.isExactlyInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> registry.getConnectionFactory(APPDIRECT_COM, getClass()))
				.isExactlyInstanceOf(IllegalArgumentException.class);
	}

	private PartnerCenterConnectionFactory createTestConnectionFactory() {
		return new PartnerCenterConnectionFactory("applicationId", "applicationSecret", "clientSecret", "tenant");
	}


}
