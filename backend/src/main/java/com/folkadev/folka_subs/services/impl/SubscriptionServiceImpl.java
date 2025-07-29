package com.folkadev.folka_subs.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.folkadev.folka_subs.domain.dto.SubscriptionDto;
import com.folkadev.folka_subs.services.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  @Override
  public Optional<List<SubscriptionDto>> getAllSubscriptions() {
    // TODO: Implement getAllSubscriptions
    return Optional.empty();
  }

  @Override
  public Optional<List<SubscriptionDto>> getSubscriptionsByServiceId(UUID serviceId) {
    // TODO: Implement getSubscriptionsByServiceId
    return Optional.empty();
  }

  @Override
  public SubscriptionDto getSubscription(UUID subscriptionId) {
    // TODO: Implement getSubscription
    return null;
  }

  @Override
  public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
    // TODO: Implement createSubscription
    return null;
  }

  @Override
  public SubscriptionDto updateSubscription(UUID subscriptionId, SubscriptionDto subscriptionDto) {
    // TODO: Implement updateSubscription
    return null;
  }

  @Override
  public void deleteSubscription(UUID subscriptionId) {
    // TODO: Implement deleteSubscription
  }
}
