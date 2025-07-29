package com.folkadev.folka_subs.repositories;

import java.util.UUID;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Consumer.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
