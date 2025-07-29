package com.folkadev.folka_subs.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.domain.entities.Subscription;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {

  List<Subscription> findSubscriptionsByServiceId(UUID serviceId);
}
