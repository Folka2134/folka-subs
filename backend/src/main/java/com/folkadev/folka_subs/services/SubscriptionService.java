package com.folkadev.folka_subs.services;

import com.folkadev.folka_subs.domain.dto.SubscriptionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionService {
  List<SubscriptionDto> getAllSubscriptions();

  List<SubscriptionDto> getSubscriptionsByServiceId(UUID serviceId);

  Optional<SubscriptionDto> getSubscription(UUID subscriptionId);

  SubscriptionDto createSubscription(SubscriptionDto subscriptionDto);

  SubscriptionDto updateSubscription(UUID subscriptionId, SubscriptionDto subscriptionDto);

  void deleteSubscription(UUID subscriptionId);
}
