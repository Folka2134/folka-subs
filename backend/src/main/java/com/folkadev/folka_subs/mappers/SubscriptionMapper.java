package com.folkadev.folka_subs.mappers;

import com.folkadev.folka_subs.domain.entities.Subscription;
import com.folkadev.folka_subs.domain.dto.SubscriptionDto;

public interface SubscriptionMapper {
  Subscription fromDto(SubscriptionDto subscriptionDto);

  SubscriptionDto toDto(Subscription subscription);
}
