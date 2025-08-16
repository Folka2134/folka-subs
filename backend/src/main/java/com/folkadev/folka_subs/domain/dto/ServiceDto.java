package com.folkadev.folka_subs.domain.dto;

import java.util.List;
import java.util.UUID;

import com.folkadev.folka_subs.domain.entities.Subscription;

import jakarta.validation.constraints.NotBlank;

public record ServiceDto(UUID id, @NotBlank String name, @NotBlank String displayName,
    List<Subscription> subscriptions) {
}
