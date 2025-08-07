package com.folkadev.folka_subs.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.services.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {
  @Override
  public List<ServiceDto> getServices() {
    // TODO: Implement getServices
    return null;
  }

  @Override
  public Optional<ServiceDto> getService(UUID serviceId) {
    // TODO: Implement getService
    return null;
  }

  @Override
  public ServiceDto createService(ServiceDto serviceDto) {
    // TODO: Implement createService
    return null;
  }

  @Override
  public ServiceDto updateService(UUID serviceId, ServiceDto serviceDto) {
    // TODO: Implement updateService
    return null;
  }

  @Override
  public void deleteService(UUID serviceId) {
    // TODO: Implement deleteService
  }
}
