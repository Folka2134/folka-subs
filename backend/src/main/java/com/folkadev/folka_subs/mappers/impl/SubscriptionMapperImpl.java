package com.folkadev.folka_subs.mappers.impl;

import org.springframework.stereotype.Component;

import com.folkadev.folka_subs.domain.dto.SubscriptionDto;
import com.folkadev.folka_subs.domain.entities.Subscription;
import com.folkadev.folka_subs.mappers.SubscriptionMapper;

@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {
  @Override
  public Subscription fromDto(SubscriptionDto subscriptionDto) {
    return new Subscription(subscriptionDto.serviceId(), subscriptionDto.price(), subscriptionDto.currency(),
        subscriptionDto.trialPeriodDays(), subscriptionDto.billingCycle(), subscriptionDto.notificationDaysBefore(),
        subscriptionDto.status(), subscriptionDto.notes(), subscriptionDto.startDate(),
        subscriptionDto.cancelledDate());
  }

  @Override
  public SubscriptionDto toDto(Subscription subscription) {
    return new SubscriptionDto(subscription.getId(), subscription.getServiceId(), subscription.getPrice(),
        subscription.getCurrency(), subscription.getTrialPeriodDays(), subscription.getBillingCycle(),
        subscription.getNotificationDaysBefore(), subscription.getStatus(), subscription.getNotes(),
        subscription.getStartDate(), subscription.getCancelledDate());
  }
}
