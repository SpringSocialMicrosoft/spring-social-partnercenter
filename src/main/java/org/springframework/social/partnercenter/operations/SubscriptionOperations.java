package org.springframework.social.partnercenter.operations;

import java.util.Optional;

import org.springframework.social.partnercenter.model.order.Subscription;

public interface SubscriptionOperations {
	Optional<Subscription> getById(String resellerCid, String id);
}
