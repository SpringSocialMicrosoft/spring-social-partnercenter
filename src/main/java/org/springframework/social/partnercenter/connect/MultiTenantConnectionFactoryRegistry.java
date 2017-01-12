package org.springframework.social.partnercenter.connect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.GenericTypeResolver;
import org.springframework.social.connect.ConnectionFactory;

public class MultiTenantConnectionFactoryRegistry {
	private final Map<String, ConnectionFactory<?>> connectionFactories = new HashMap<String, ConnectionFactory<?>>();

	private final Map<Class<?>, String> apiTypeIndex = new HashMap<Class<?>, String>();

	/**
	 * Add a {@link ConnectionFactory} to this registry.
	 * @param connectionFactory the connection factory
	 */
	public void addConnectionFactory(String tenant, ConnectionFactory<?> connectionFactory) {
		if (connectionFactories.containsKey(buildKey(tenant, connectionFactory.getProviderId()))) {
			throw new IllegalArgumentException("A ConnectionFactory for provider '" + connectionFactory.getProviderId() + "' with tenantId '"+tenant+"' has already been registered");
		}
		Class<?> apiType = GenericTypeResolver.resolveTypeArgument(connectionFactory.getClass(), ConnectionFactory.class);
		if (!apiTypeIndex.containsKey(apiType)) {
			apiTypeIndex.put(apiType, connectionFactory.getProviderId());
		}
		connectionFactories.put(buildKey(tenant, connectionFactory.getProviderId()), connectionFactory);
	}

	public void setConnectionFactories(Map<String, ConnectionFactory<?>> connectionFactories) {
		for(String key : connectionFactories.keySet()){
			addConnectionFactory(key, connectionFactories.get(key));
		}
	}

	// implementing ConnectionFactoryLocator

	public ConnectionFactory<?> getConnectionFactory(String tenantId, String providerId) {
		ConnectionFactory<?> connectionFactory = connectionFactories.get(buildKey(tenantId, providerId));
		if (connectionFactory == null) {
			throw new IllegalArgumentException("No connection factory for service provider '" + providerId + "' with tenantId '"+tenantId+"'is registered");
		}
		return connectionFactory;
	}

	@SuppressWarnings("unchecked")
	public <A> ConnectionFactory<A> getConnectionFactory(String tenantId, Class<A> apiType) {
		String providerId = apiTypeIndex.get(apiType);
		if (providerId == null) {
			throw new IllegalArgumentException("No connection factory for API [" + apiType.getName() + "] with tenantId '"+tenantId+"' is registered");
		}
		return (ConnectionFactory<A>) getConnectionFactory(tenantId, providerId);
	}

	public boolean isRegistered(String tenantId, String providerId){
		return connectionFactories.containsKey(buildKey(tenantId, providerId));
	}

	public boolean isRegistered(String tenantId, Class<?> apiType){
		String providerId = apiTypeIndex.get(apiType);
		return providerId != null && connectionFactories.containsKey(buildKey(tenantId, providerId));
	}

	public Set<String> registeredTenantIds() {
		return connectionFactories.keySet()
				.stream().map(s -> s.split(":")[0])
				.collect(Collectors.toSet());
	}

	public Set<String> registeredProviderIds() {
		return new HashSet<>(apiTypeIndex.values());
	}

	private String buildKey(String tenantId, String providerId){
		return tenantId.concat(":").concat(providerId);
	}
}
