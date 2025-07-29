package com.folkadev.folka_subs.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.folkadev.folka_subs.domain.entities.BillingCycle;
import com.folkadev.folka_subs.domain.entities.Status;

public record SubscriptionDto(
    UUID id,
    UUID serviceId,
    int price,
    String currency,
    int trialPeriodDays,
    BillingCycle billingCycle,
    List<Integer> notificationDaysBefore,
    Status status,
    String notes,
    LocalDateTime startDate,
    LocalDateTime cancelledDate) {
}
