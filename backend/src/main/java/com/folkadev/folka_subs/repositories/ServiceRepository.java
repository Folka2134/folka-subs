package com.folkadev.folka_subs.repositories;

import java.util.Optional;
import com.folkadev.folka_subs.domain.entities.Service;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {

  Optional<Service> findByName(String name);
}
