package com.folkadev.folka_subs.services;

import com.folkadev.folka_subs.domain.dto.ServiceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceService {
  List<ServiceDto> getServices();

  Optional<ServiceDto> getService(UUID serviceId);

  ServiceDto createService(ServiceDto serviceDto);

  ServiceDto updateService(UUID serviceId, ServiceDto serviceDto);

  void deleteService(UUID serviceId);
}
