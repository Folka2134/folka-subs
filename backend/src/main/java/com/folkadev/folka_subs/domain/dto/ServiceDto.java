package com.folkadev.folka_subs.domain.dto;

import java.util.List;
import java.util.UUID;

import com.folkadev.folka_subs.domain.entities.Subscription;

public record ServiceDto(UUID id, String name, String displayName, List<Subscription> subscriptions) {
}
