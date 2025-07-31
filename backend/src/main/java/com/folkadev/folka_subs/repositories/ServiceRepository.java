package com.folkadev.folka_subs.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.folkadev.folka_subs.domain.entities.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
