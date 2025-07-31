package com.folkadev.folka_subs.mappers.impl;

import org.springframework.stereotype.Component;

import com.folkadev.folka_subs.domain.dto.SubscriptionDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.domain.entities.Subscription;
import com.folkadev.folka_subs.mappers.SubscriptionMapper;
import com.folkadev.folka_subs.repositories.ServiceRepository;

@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

  private final ServiceRepository serviceRepository;

  public SubscriptionMapperImpl(ServiceRepository serviceRepository) {
    this.serviceRepository = serviceRepository;
  }

  @Override
  public Subscription fromDto(SubscriptionDto subscriptionDto) {
    Service service = serviceRepository.findById(subscriptionDto.serviceId())
        .orElseThrow(() -> new RuntimeException("Service not found"));
    return new Subscription(service, subscriptionDto.price(), subscriptionDto.currency(),
        subscriptionDto.trialPeriodDays(), subscriptionDto.billingCycle(), subscriptionDto.notificationDaysBefore(),
        subscriptionDto.status(), subscriptionDto.notes(), subscriptionDto.startDate(),
        subscriptionDto.cancelledDate());
  }

  @Override
  public SubscriptionDto toDto(Subscription subscription) {
    return new SubscriptionDto(subscription.getId(), subscription.getService().getId(), subscription.getPrice(),
        subscription.getCurrency(), subscription.getTrialPeriodDays(), subscription.getBillingCycle(),
        subscription.getNotificationDaysBefore(), subscription.getStatus(), subscription.getNotes(),
        subscription.getStartDate(), subscription.getCancelledDate());
  }
}