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
  public List<SubscriptionDto> getAllSubscriptions() {
    // TODO: Implement getAllSubscriptions
    return null;
  }

  @Override
  public List<SubscriptionDto> getSubscriptionsByServiceId(UUID serviceId) {
    // TODO: Implement getSubscriptionsByServiceId
    return null;
  }

  @Override
  public Optional<SubscriptionDto> getSubscription(UUID subscriptionId) {
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
